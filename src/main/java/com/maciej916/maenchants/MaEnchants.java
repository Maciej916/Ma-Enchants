package com.maciej916.maenchants;

import com.maciej916.maenchants.network.Networking;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod(MaEnchants.MODID)
public class MaEnchants {
    public static final String MODID = "ma-enchants";

    public MaEnchants() {
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
//        modLoadingContext.registerConfig(ModConfig.Type.CLIENT, ConfigHolder.CLIENT_SPEC);
//        modLoadingContext.registerConfig(ModConfig.Type.SERVER, ConfigHolder.SERVER_SPEC);
        Networking.registerMessages();
    }
}