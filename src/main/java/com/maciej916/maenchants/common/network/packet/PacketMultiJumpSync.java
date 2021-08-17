package com.maciej916.maenchants.common.network.packet;

import com.maciej916.maenchants.common.capabilities.mod.IModCapability;
import com.maciej916.maenchants.common.util.PlayerUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

import static com.maciej916.maenchants.MaEnchants.proxy;

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
            Player player = proxy.getClientPlayer();
            IModCapability enchantsCap = PlayerUtil.getEnchantsCapability(player);
            enchantsCap.setMultiJump(multiJump);
        });
        ctx.get().setPacketHandled(true);
    }
}
