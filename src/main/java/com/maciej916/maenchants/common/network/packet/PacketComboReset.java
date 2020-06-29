package com.maciej916.maenchants.common.network.packet;

import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketComboReset {

    public PacketComboReset() {}


    public PacketComboReset(PacketBuffer buf) {
    }

    public void toBytes(PacketBuffer buf) {
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (ctx.get() == null || ctx.get().getSender() == null) return;
            ItemStack stack = ctx.get().getSender().getHeldItem(Hand.MAIN_HAND);
            stack.getOrCreateTag().putInt("combo", 0);
        });
        ctx.get().setPacketHandled(true);
    }
}