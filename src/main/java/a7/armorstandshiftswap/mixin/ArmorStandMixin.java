package a7.armorstandshiftswap.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorStand.class)
public class ArmorStandMixin {
    @Shadow
    private boolean isDisabled(EquipmentSlot slot) { return false; }

    @Inject(method = "interactAt", at = @At("HEAD"), cancellable = true)
    public void interactAt(Player player, Vec3 vec, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        if (!player.isShiftKeyDown())
            return;

        ArmorStand $this = (ArmorStand) (Object) this;
        ItemStack itemstack = player.getItemInHand(hand);

        if (!$this.isMarker() && !itemstack.is(Items.NAME_TAG) && !player.isSpectator()) {
            if (player.level().isClientSide) {
                cir.setReturnValue(InteractionResult.CONSUME);
                cir.cancel();
            } else {
                for (int i = 0; i < 4; i++) {
                    var slot = EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, i);
                    if (!this.isDisabled(slot)) {
                        ItemStack playerStack = player.getItemBySlot(slot);
                        ItemStack myStack = $this.getItemBySlot(slot);
                        player.setItemSlot(slot, myStack);
                        $this.setItemSlot(slot, playerStack);
                    }
                }
                cir.setReturnValue(InteractionResult.SUCCESS);
                cir.cancel();
            }
        }
    }
}
