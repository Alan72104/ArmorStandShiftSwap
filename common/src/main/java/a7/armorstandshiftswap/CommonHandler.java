package a7.armorstandshiftswap;

import a7.armorstandshiftswap.client.ArmorStandShiftSwapClient;
import a7.armorstandshiftswap.packets.SwapArmorSetPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class CommonHandler {
    public static ActionResult entityInteract(PlayerEntity player, Hand hand, Entity entity) {
        if (player.getWorld().isClient &&
                ArmorStandShiftSwapClient.KEYBINDING_SWAP.isUnbound() &&
                entity instanceof ArmorStandEntity armorStand &&
                player.isSneaking() &&
                !armorStand.isMarker() &&
                !player.getStackInHand(hand).isOf(Items.NAME_TAG) &&
                !player.isSpectator()) {
            PacketHandler.sendToServer(new SwapArmorSetPacket(armorStand.getId()));
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}
