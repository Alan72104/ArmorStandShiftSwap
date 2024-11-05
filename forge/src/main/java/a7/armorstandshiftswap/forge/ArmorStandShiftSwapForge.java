package a7.armorstandshiftswap.forge;

import a7.armorstandshiftswap.ArmorStandShiftSwap;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ArmorStandShiftSwap.MOD_ID)
public final class ArmorStandShiftSwapForge {
    public ArmorStandShiftSwapForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(ArmorStandShiftSwap.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        ArmorStandShiftSwap.init();
    }
}
