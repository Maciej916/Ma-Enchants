package com.maciej916.maenchants.network;

import com.maciej916.maenchants.capabilities.IEnchants;
import com.maciej916.maenchants.utils.PlayerUtil;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketLumberjackToggle {

    private final boolean toggle;

    public PacketLumberjackToggle(boolean toggle) {
        this.toggle = toggle;
    }

    public PacketLumberjackToggle(PacketBuffer buf) {
        toggle = buf.readBoolean();
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeBoolean(toggle);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (ctx.get() == null || ctx.get().getSender() == null) return;
            IEnchants enchantsCap = PlayerUtil.getEnchantsCapability(ctx.get().getSender());
            enchantsCap.setExcavateActive(toggle);
        });
        ctx.get().setPacketHandled(true);
    }
}
