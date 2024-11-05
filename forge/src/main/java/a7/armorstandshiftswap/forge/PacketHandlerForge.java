package a7.armorstandshiftswap.forge;

import a7.armorstandshiftswap.PacketHandler;
import a7.armorstandshiftswap.packets.IPacket;
import a7.armorstandshiftswap.packets.SwapArmorSetPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Optional;
import java.util.function.Function;

public class PacketHandlerForge {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new Identifier("armorstandshiftswap", "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );
    private static int index = 0;

    public static void register() {
        registerPacket(SwapArmorSetPacket.class, SwapArmorSetPacket::new, NetworkDirection.PLAY_TO_SERVER);
        PacketHandler.sendToServerImpl = INSTANCE::sendToServer;
    }

    private static <T extends IPacket> void registerPacket(
            Class<T> clazz,
            Function<PacketByteBuf, T> factory,
            NetworkDirection direction) {
        INSTANCE.registerMessage(
                index++,
                clazz,
                T::write,
                factory,
                (packet, contextSupplier) -> {
                    NetworkEvent.Context ctx = contextSupplier.get();
                    ctx.enqueueWork(() -> {
                        PlayerEntity sender = ctx.getSender();
                        if (sender != null && sender.getWorld() == null)
                            return;
                        packet.handle(sender);
                    });
                    ctx.setPacketHandled(true);
                },
                Optional.of(direction)
        );
    }
}
