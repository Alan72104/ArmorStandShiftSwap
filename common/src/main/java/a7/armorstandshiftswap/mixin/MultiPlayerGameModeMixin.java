package a7.armorstandshiftswap.mixin;

import a7.armorstandshiftswap.client.ArmorStandShiftSwapClient;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MultiPlayerGameMode.class)
public class MultiPlayerGameModeMixin {
    @Inject(method = "interactAt", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;ensureHasSentCarriedItem()V",
            shift = At.Shift.AFTER), cancellable = true)
    public void asss$interactAt(Player player,
                           Entity entity,
                           EntityHitResult entityHitResult,
                           InteractionHand interactionHand,
                           CallbackInfoReturnable<InteractionResult> cir) {
        InteractionResult res = ArmorStandShiftSwapClient.mixinOnInteract(player, entity, interactionHand);
        if (res != InteractionResult.PASS) {
            cir.setReturnValue(res);
            cir.cancel();
        }
    }
}
