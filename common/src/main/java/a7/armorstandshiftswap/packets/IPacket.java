package a7.armorstandshiftswap.packets;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import org.jetbrains.annotations.Nullable;

public interface IPacket {
    void write(PacketByteBuf buf);

    void handle(@Nullable PlayerEntity sender);
}
