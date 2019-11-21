package com.maciej916.maenchants.network;

import com.maciej916.maenchants.MaEnchants;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class Networking {

    public static SimpleChannel INSTANCE;
    private static int ID = 0;

    private static int nextID() {
        return ID++;
    }

    public static void registerMessages() {
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(MaEnchants.MODID, "ma-enchants"), () -> "1.0", s -> true, s -> true);
        INSTANCE.registerMessage(nextID(), PacketComboReset.class, PacketComboReset::toBytes, PacketComboReset::new, PacketComboReset::handle);
        INSTANCE.registerMessage(nextID(), PacketMultiJumpSync.class, PacketMultiJumpSync::toBytes, PacketMultiJumpSync::new, PacketMultiJumpSync::handle);
        INSTANCE.registerMessage(nextID(), PacketMultiJumpDo.class, PacketMultiJumpDo::toBytes, PacketMultiJumpDo::new, PacketMultiJumpDo::handle);
        INSTANCE.registerMessage(nextID(), PacketLumberjackToggle.class, PacketLumberjackToggle::toBytes, PacketLumberjackToggle::new, PacketLumberjackToggle::handle);
    }

}