package a7.armorstandshiftswap;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

public final class ArmorStandShiftSwap {
    public static final String MOD_ID = "armorstandshiftswap";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static void init() {
        LOGGER.info("ArmorStandShiftSwap installed, shift right click an armor stand to swap armor set");
    }
}
