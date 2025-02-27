package a7.armorstandshiftswap.client;

import a7.armorstandshiftswap.packets.SwapArmorSetPacket;
import com.mojang.blaze3d.platform.InputConstants;
import dev.architectury.event.events.client.ClientLifecycleEvent;
import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.networking.NetworkManager;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class ArmorStandShiftSwapClient {
    public static final KeyMapping KEY_MAPPING_SWAP = new KeyMapping(
            "key.armorstandshiftswap.swap",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "key.categories.armorstandshiftswap.main"
    );

    public static void init() {
        KeyMappingRegistry.register(KEY_MAPPING_SWAP);
        ClientTickEvent.CLIENT_POST.register(ArmorStandShiftSwapClient::onClientTickPost);
    }

    private static void onClientTickPost(Minecraft mc) {
        while (KEY_MAPPING_SWAP.consumeClick()) {
            onKeyBindSwap();
        }
    }

    public static InteractionResult mixinOnInteract(Player player, Entity entity, InteractionHand hand) {
        if (ArmorStandShiftSwapClient.KEY_MAPPING_SWAP.isUnbound() &&
                entity instanceof ArmorStand armorStand &&
                player.isShiftKeyDown() &&
                !armorStand.isMarker() &&
                !player.getItemInHand(hand).is(Items.NAME_TAG) &&
                !player.isSpectator()) {
            NetworkManager.sendToServer(new SwapArmorSetPacket(armorStand.getId()));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    private static void onKeyBindSwap() {
        Minecraft mc = Minecraft.getInstance();
        if (!(mc.player != null &&
                mc.level != null &&
                mc.screen == null &&
                mc.hitResult != null &&
                mc.hitResult.getType() == HitResult.Type.ENTITY &&
                !mc.player.isSpectator()))
            return;
        Entity target = ((EntityHitResult) mc.hitResult).getEntity();
        if (!(target instanceof ArmorStand armorStand &&
                !armorStand.isMarker() &&
                mc.level.getWorldBorder().isWithinBounds(target.blockPosition())))
            return;
        NetworkManager.sendToServer(new SwapArmorSetPacket(armorStand.getId()));
    }
}
