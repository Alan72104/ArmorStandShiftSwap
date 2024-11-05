package a7.armorstandshiftswap.forge;

import a7.armorstandshiftswap.ArmorStandShiftSwap;
import a7.armorstandshiftswap.CommonHandler;
import net.minecraft.util.ActionResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(ArmorStandShiftSwap.MOD_ID)
public final class ArmorStandShiftSwapForge {
    public ArmorStandShiftSwapForge() {
        ArmorStandShiftSwap.init();
        PacketHandlerForge.register();
    }

    @Mod.EventBusSubscriber(modid = ArmorStandShiftSwap.MOD_ID)
    public static class EventHandlerForge {
        @SubscribeEvent
        public static void entityInteractSpecific(PlayerInteractEvent.EntityInteractSpecific event) {
            ActionResult res = CommonHandler.entityInteract(
                    event.getEntity(), event.getHand(), event.getTarget());
            if (res != ActionResult.PASS) {
                event.setCancellationResult(res);
                event.setCanceled(true);
            }
        }
    }
}
