package a7.armorstandshiftswap.packets;

import a7.armorstandshiftswap.mixin.accessors.ArmorStandEntityAccessor;
import dev.architectury.networking.NetworkManager.PacketContext;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.Supplier;

public class SwapArmorSetPacket implements IPacket {
    public final int armorStandId;

    public SwapArmorSetPacket(int armorStandId) {
        this.armorStandId = armorStandId;
    }

    public SwapArmorSetPacket(FriendlyByteBuf buf) {
        this(buf.readInt());
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(armorStandId);
    }

    @Override
    public void handle(Supplier<PacketContext> contextSupplier) {
        PacketContext ctx = contextSupplier.get();
        Player player = ctx.getPlayer();

        Entity entity = player.getLevel().getEntity(armorStandId);
        if (!(entity instanceof ArmorStand armorStand))
            return;

        if (!armorStand.isMarker() &&
                !player.getMainHandItem().is(Items.NAME_TAG) &&
                !player.isSpectator()) {
            for (int i = 0; i < 4; i++) {
                EquipmentSlot slot = EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, i);
                if (!((ArmorStandEntityAccessor) armorStand).asss$isDisabled(slot)) {
                    ItemStack playerStack = player.getItemBySlot(slot);
                    ItemStack asStack = armorStand.getItemBySlot(slot);
                    player.setItemSlot(slot, asStack);
                    armorStand.setItemSlot(slot, playerStack);
                }
            }
        }
    }
}
