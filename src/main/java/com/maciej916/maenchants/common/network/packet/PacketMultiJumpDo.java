package com.maciej916.maenchants.common.network.packet;

import com.maciej916.maenchants.common.handler.HandlerMultiJump;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketMultiJumpDo {

    public PacketMultiJumpDo() {}

    public PacketMultiJumpDo(ByteBuf buf) {
    }

    public void toBytes(ByteBuf buf) {
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (ctx.get() == null || ctx.get().getSender() == null) return;
            Level world = ctx.get().getSender().getCommandSenderWorld();
            ServerPlayer player = ctx.get().getSender();
            HandlerMultiJump.handlerJump(player);
            world.addParticle(ParticleTypes.CLOUD, player.getX(), player.getY(), player.getZ(), 15, 0.25F, 0.0F);
        });
        ctx.get().setPacketHandled(true);
    }
}
