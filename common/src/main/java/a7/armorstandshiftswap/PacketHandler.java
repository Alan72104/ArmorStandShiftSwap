package a7.armorstandshiftswap;

import a7.armorstandshiftswap.packets.IPacket;

import java.util.function.Consumer;

public class PacketHandler {
    public static Consumer<IPacket> sendToServerImpl = null;

    public static void sendToServer(IPacket packet) {
        sendToServerImpl.accept(packet);
    }
}
