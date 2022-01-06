package com.maciej916.maenchants.common.network.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketCloudParticles {

    public PacketCloudParticles() {}

    public PacketCloudParticles(ByteBuf buf) {
    }

    public void toBytes(ByteBuf buf) {
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (ctx.get() == null || ctx.get().getSender() == null) return;
            Level world = ctx.get().getSender().getCommandSenderWorld();
            Player player = ctx.get().getSender();
            world.addParticle(ParticleTypes.CLOUD, player.getX(), player.getY(), player.getZ(), 15, 0.25F, 0.0F);
        });
        ctx.get().setPacketHandled(true);
    }
}
