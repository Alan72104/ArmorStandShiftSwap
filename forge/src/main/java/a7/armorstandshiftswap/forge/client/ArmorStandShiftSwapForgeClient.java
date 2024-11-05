package a7.armorstandshiftswap.forge.client;

import a7.armorstandshiftswap.ArmorStandShiftSwap;
import a7.armorstandshiftswap.client.ArmorStandShiftSwapClient;
import a7.armorstandshiftswap.client.CommonHandlerClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
public class ArmorStandShiftSwapForgeClient {
    @Mod.EventBusSubscriber(modid = ArmorStandShiftSwap.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class EventHandlerForgeModClient {
        @SubscribeEvent
        public static void registerBindings(RegisterKeyMappingsEvent event) {
            ArmorStandShiftSwapClient.registerKeyBinds(event::register);
        }
    }

    @Mod.EventBusSubscriber(modid = ArmorStandShiftSwap.MOD_ID, value = Dist.CLIENT)
    public static class EventHandlerForgeClient {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.END)
                CommonHandlerClient.endTick();
        }
    }
}
