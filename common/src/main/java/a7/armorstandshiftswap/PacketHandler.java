package a7.armorstandshiftswap;

import a7.armorstandshiftswap.packets.IPacket;
import a7.armorstandshiftswap.packets.SwapArmorSetPacket;
import dev.architectury.networking.NetworkChannel;
import net.minecraft.resources.ResourceLocation;

public class PacketHandler {
    public static final NetworkChannel CHANNEL = NetworkChannel.create(new ResourceLocation(ArmorStandShiftSwap.MOD_ID, "main"));

    public static void register() {
        CHANNEL.register(SwapArmorSetPacket.class, SwapArmorSetPacket::encode, SwapArmorSetPacket::new, SwapArmorSetPacket::handle);
    }

    public static void sendToServer(IPacket packet) {
        CHANNEL.sendToServer(packet);
    }
}
