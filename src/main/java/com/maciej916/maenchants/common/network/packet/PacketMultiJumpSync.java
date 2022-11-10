package com.maciej916.maenchants.common.network.packet;

import com.maciej916.maenchants.common.capabilities.player.IPlayerCapability;
import com.maciej916.maenchants.common.util.PlayerUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketMultiJumpSync {

    private final int multiJump;

    public PacketMultiJumpSync(int multiJump) {
        this.multiJump = multiJump;
    }

    public PacketMultiJumpSync(ByteBuf buf) {
        multiJump = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(multiJump);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            assert player != null;
            IPlayerCapability enchantsCap = PlayerUtil.getEnchantsCapability(player);
            enchantsCap.setMultiJump(multiJump);
        });
        ctx.get().setPacketHandled(true);
    }
}
