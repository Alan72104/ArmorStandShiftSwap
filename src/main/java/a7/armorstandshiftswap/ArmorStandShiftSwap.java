package a7.armorstandshiftswap;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ArmorStandShiftSwap.MODID)
public class ArmorStandShiftSwap
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "armorstandshiftswap";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public ArmorStandShiftSwap()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("ArmorStandShiftSwap installed, shift right click armor stand to swap armor");
    }
}
