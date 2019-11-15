package com.maciej916.maenchants.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class Keys {
    public static final KeyBinding excavateKey = new KeyBinding("key.ma-enchants.key.excavate", 67, "Ma Enchants");

    public static void registerKeys() {
        ClientRegistry.registerKeyBinding(excavateKey);
    }
}