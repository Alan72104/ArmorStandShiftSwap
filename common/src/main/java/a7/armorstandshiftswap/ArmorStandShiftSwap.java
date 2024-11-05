package a7.armorstandshiftswap;

import a7.armorstandshiftswap.client.ArmorStandShiftSwapClient;
import com.mojang.logging.LogUtils;
import dev.architectury.platform.Platform;
import dev.architectury.utils.Env;
import org.slf4j.Logger;

public final class ArmorStandShiftSwap {
    public static final String MOD_ID = "armorstandshiftswap";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static void init() {
        PacketHandler.register();

        if (Platform.getEnvironment() == Env.CLIENT) {
            ArmorStandShiftSwapClient.init();
        }

        LOGGER.info("ArmorStandShiftSwap installed, shift right click an armor stand to swap armor set");
    }
}
