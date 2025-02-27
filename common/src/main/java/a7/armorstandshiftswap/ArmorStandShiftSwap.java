package a7.armorstandshiftswap;

import a7.armorstandshiftswap.client.ArmorStandShiftSwapClient;
import a7.armorstandshiftswap.packets.SwapArmorSetPacket;
import com.mojang.logging.LogUtils;
import dev.architectury.networking.NetworkManager;
import dev.architectury.platform.Platform;
import dev.architectury.utils.Env;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

public final class ArmorStandShiftSwap {
    public static final String MOD_ID = "armorstandshiftswap";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static void init() {
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, SwapArmorSetPacket.TYPE, SwapArmorSetPacket.STREAM_CODEC, SwapArmorSetPacket::handle);

        if (Platform.getEnvironment() == Env.CLIENT) {
            ArmorStandShiftSwapClient.init();
        }

        LOGGER.info("ArmorStandShiftSwap installed, shift right click an armor stand to swap armor set");
    }
}
