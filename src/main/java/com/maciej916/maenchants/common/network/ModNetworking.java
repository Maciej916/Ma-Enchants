package com.maciej916.maenchants.common.network;

import com.maciej916.maenchants.MaEnchants;
import com.maciej916.maenchants.common.network.packet.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModNetworking {

    public static SimpleChannel INSTANCE;
    private static int ID = 0;

    private static int nextID() {
        return ID++;
    }

    public static void setup() {
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(MaEnchants.MODID, MaEnchants.MODID), () -> "1.0", s -> true, s -> true);
        INSTANCE.registerMessage(nextID(), PacketComboReset.class, PacketComboReset::toBytes, PacketComboReset::new, PacketComboReset::handle);
        INSTANCE.registerMessage(nextID(), PacketMultiJumpSync.class, PacketMultiJumpSync::toBytes, PacketMultiJumpSync::new, PacketMultiJumpSync::handle);
        INSTANCE.registerMessage(nextID(), PacketMultiJumpDo.class, PacketMultiJumpDo::toBytes, PacketMultiJumpDo::new, PacketMultiJumpDo::handle);
        INSTANCE.registerMessage(nextID(), PacketLumberjackToggle.class, PacketLumberjackToggle::toBytes, PacketLumberjackToggle::new, PacketLumberjackToggle::handle);

        INSTANCE.registerMessage(nextID(), PacketCloudParticles.class, PacketCloudParticles::toBytes, PacketCloudParticles::new, PacketCloudParticles::handle);
    }
}