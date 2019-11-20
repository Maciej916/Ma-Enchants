package com.maciej916.maenchants.network;

import com.maciej916.maenchants.capabilities.IEnchants;
import com.maciej916.maenchants.utils.PlayerUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

import static com.maciej916.maenchants.MaEnchants.proxy;

public class PacketMultiJumpSync {

    private final int multiJump;

    public PacketMultiJumpSync(int multiJump) {
        this.multiJump = multiJump;
    }

    public PacketMultiJumpSync(PacketBuffer buf) {
        multiJump = buf.readInt();
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeInt(multiJump);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            PlayerEntity player = proxy.getClientPlayer();
            IEnchants enchantsCap = PlayerUtil.getEnchantsCapability(player);
            enchantsCap.setMultiJump(multiJump);
        });
        ctx.get().setPacketHandled(true);
    }
}
