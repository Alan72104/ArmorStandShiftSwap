package a7.armorstandshiftswap.packets;

import a7.armorstandshiftswap.mixin.accessors.ArmorStandEntityAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import org.jetbrains.annotations.Nullable;

public class SwapArmorSetPacket implements IPacket {
    public final int armorStandId;

    public SwapArmorSetPacket(int armorStandId) {
        this.armorStandId = armorStandId;
    }

    public SwapArmorSetPacket(PacketByteBuf buf) {
        this(buf.readInt());
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeInt(armorStandId);
    }

    @Override
    public void handle(@Nullable PlayerEntity player) {
        assert player != null;

        Entity entity = player.getWorld().getEntityById(armorStandId);
        if (!(entity instanceof ArmorStandEntity armorStand))
            return;

        if (!armorStand.isMarker() &&
                !player.getMainHandStack().isOf(Items.NAME_TAG) &&
                !player.isSpectator()) {
            for (int i = 0; i < 4; i++) {
                EquipmentSlot slot = EquipmentSlot.fromTypeIndex(EquipmentSlot.Type.ARMOR, i);
                if (!((ArmorStandEntityAccessor) armorStand).asss$isSlotDisabled(slot)) {
                    ItemStack playerStack = player.getEquippedStack(slot);
                    ItemStack asStack = armorStand.getEquippedStack(slot);
                    player.equipStack(slot, asStack);
                    armorStand.equipStack(slot, playerStack);
                }
            }
        }
    }
}
