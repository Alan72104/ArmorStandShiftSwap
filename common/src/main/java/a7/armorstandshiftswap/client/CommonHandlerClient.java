package a7.armorstandshiftswap.client;

import a7.armorstandshiftswap.PacketHandler;
import a7.armorstandshiftswap.packets.SwapArmorSetPacket;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

public class CommonHandlerClient {
    public static void endTick() {
        while (ArmorStandShiftSwapClient.KEYBINDING_SWAP.wasPressed()) {
            onKeyBindSwap();
        }
    }

    private static void onKeyBindSwap() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (!(mc.player != null &&
                mc.world != null &&
                mc.currentScreen == null &&
                mc.crosshairTarget != null &&
                mc.crosshairTarget.getType() == HitResult.Type.ENTITY &&
                !mc.player.isSpectator()))
            return;
        Entity target = ((EntityHitResult) mc.crosshairTarget).getEntity();
        if (!(target instanceof ArmorStandEntity armorStand &&
                !armorStand.isMarker() &&
                mc.world.getWorldBorder().contains(target.getBlockPos())))
            return;
        PacketHandler.sendToServer(new SwapArmorSetPacket(armorStand.getId()));
    }
}
