package com.maciej916.maenchants.common.network.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketComboReset {

    public PacketComboReset() {}


    public PacketComboReset(ByteBuf buf) {
    }

    public void toBytes(ByteBuf buf) {
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (ctx.get() == null || ctx.get().getSender() == null) return;
            ItemStack stack = ctx.get().getSender().getItemInHand(InteractionHand.MAIN_HAND);
            stack.getOrCreateTag().putInt("combo", 0);
        });
        ctx.get().setPacketHandled(true);
    }
}