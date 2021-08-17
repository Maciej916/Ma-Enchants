package com.maciej916.maenchants.common.network.packet;

import com.maciej916.maenchants.common.capabilities.mod.IModCapability;
import com.maciej916.maenchants.common.util.PlayerUtil;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketLumberjackToggle {

    private final boolean toggle;

    public PacketLumberjackToggle(boolean toggle) {
        this.toggle = toggle;
    }

    public PacketLumberjackToggle(ByteBuf buf) {
        toggle = buf.readBoolean();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(toggle);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (ctx.get() == null || ctx.get().getSender() == null) return;
            IModCapability enchantsCap = PlayerUtil.getEnchantsCapability(ctx.get().getSender());
            enchantsCap.setExcavateActive(toggle);
        });
        ctx.get().setPacketHandled(true);
    }
}
