package com.maciej916.maenchants.client;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;

public class Keys {
    public static final KeyMapping excavateKey = new KeyMapping("key.maenchants.key.excavate", 67, "Ma Enchants");

    public static void registerKeys() {
        ClientRegistry.registerKeyBinding(excavateKey);
    }
}