package com.maciej916.maenchants.network;

import com.maciej916.maenchants.handler.HandlerLumberjack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketLumberjack {

    private final BlockPos pos;

    public PacketLumberjack(BlockPos pos) {
        this.pos = pos;
    }

    public PacketLumberjack(PacketBuffer buf) {
        pos = buf.readBlockPos();
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeBlockPos(pos);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (ctx.get() == null || ctx.get().getSender() == null) return;
            ServerPlayerEntity player = ctx.get().getSender();
            ServerWorld world = ctx.get().getSender().getServerWorld();
            HandlerLumberjack.doBreak(player, world, pos);
        });
        ctx.get().setPacketHandled(true);
    }
}