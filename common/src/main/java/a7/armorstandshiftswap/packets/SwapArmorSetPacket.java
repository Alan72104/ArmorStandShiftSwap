package a7.armorstandshiftswap.packets;

import a7.armorstandshiftswap.ArmorStandShiftSwap;
import a7.armorstandshiftswap.mixin.accessors.ArmorStandEntityAccessor;
import dev.architectury.networking.NetworkManager.PacketContext;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public record SwapArmorSetPacket(int armorStandId) implements CustomPacketPayload {
    public static final Type<SwapArmorSetPacket> TYPE =
            new Type<>(ArmorStandShiftSwap.id("swap_armor_set"));
    public static final StreamCodec<RegistryFriendlyByteBuf, SwapArmorSetPacket> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.INT, SwapArmorSetPacket::armorStandId,
                    SwapArmorSetPacket::new
            );
    private static final EquipmentSlot[] ARMOR_SLOTS = {
            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET
    };

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(PacketContext ctx) {
        Player player = ctx.getPlayer();
        ctx.queue(() -> {
            Entity entity = player.level().getEntity(armorStandId);
            if (!(entity instanceof ArmorStand armorStand))
                return;

            if (!armorStand.isMarker() &&
                    !player.getMainHandItem().is(Items.NAME_TAG) &&
                    !player.isSpectator()) {
                for (EquipmentSlot slot : ARMOR_SLOTS) {
                    if (!((ArmorStandEntityAccessor) armorStand).asss$isDisabled(slot)) {
                        ItemStack playerStack = player.getItemBySlot(slot);
                        ItemStack asStack = armorStand.getItemBySlot(slot);
                        player.setItemSlot(slot, asStack);
                        armorStand.setItemSlot(slot, playerStack);
                    }
                }
            }
        });
    }
}
