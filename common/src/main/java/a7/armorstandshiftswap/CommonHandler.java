package a7.armorstandshiftswap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class CommonHandler {
    public static ActionResult entityInteract(PlayerEntity player, Hand hand, Entity entity) {
        if (!player.isSneaking())
            return ActionResult.PASS;

        if (!(entity instanceof ArmorStandEntity as))
            return ActionResult.PASS;

        ItemStack stack = player.getStackInHand(hand);

        if (!as.isMarker() && !stack.isOf(Items.NAME_TAG) && !player.isSpectator()) {
            if (player.getWorld().isClient) {
                return ActionResult.SUCCESS;
            } else {
                for (int i = 0; i < 4; i++) {
                    EquipmentSlot slot = EquipmentSlot.fromTypeIndex(EquipmentSlot.Type.ARMOR, i);
                    if (!as.isSlotDisabled(slot)) {
                        ItemStack playerStack = player.getEquippedStack(slot);
                        ItemStack asStack = as.getEquippedStack(slot);
                        player.equipStack(slot, asStack);
                        as.equipStack(slot, playerStack);
                    }
                }
                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS;
    }
}
