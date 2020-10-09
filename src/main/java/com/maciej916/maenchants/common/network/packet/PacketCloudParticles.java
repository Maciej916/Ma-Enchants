package com.maciej916.maenchants.common.network.packet;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketCloudParticles {

    public PacketCloudParticles() {}

    public PacketCloudParticles(PacketBuffer buf) {
    }

    public void toBytes(PacketBuffer buf) {
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (ctx.get() == null || ctx.get().getSender() == null) return;
            ServerWorld world = ctx.get().getSender().getServerWorld();
            ServerPlayerEntity player = ctx.get().getSender();
            world.spawnParticle(ParticleTypes.CLOUD, player.getPosX(), player.getPosY(), player.getPosZ(), 15, 0.25F, 0.1F, 0.25F, 0.01F);
        });
        ctx.get().setPacketHandled(true);
    }
}
