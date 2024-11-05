package a7.armorstandshiftswap.packets;

import dev.architectury.networking.NetworkManager.PacketContext;
import net.minecraft.network.FriendlyByteBuf;

import java.util.function.Supplier;

public interface IPacket {
    void encode(FriendlyByteBuf buf);

    void handle(Supplier<PacketContext> contextSupplier);
}
