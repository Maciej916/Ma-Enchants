package com.maciej916.maenchants;

import com.maciej916.maenchants.capabilities.Enchants;
import com.maciej916.maenchants.capabilities.EnchantsStorage;
import com.maciej916.maenchants.capabilities.IEnchants;
import com.maciej916.maenchants.client.Keys;
import com.maciej916.maenchants.network.Networking;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MaEnchants.MODID)
public class MaEnchants {
    public static final String MODID = "ma-enchants";

    public MaEnchants() {
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
//        modLoadingContext.registerConfig(ModConfig.Type.CLIENT, ConfigHolder.CLIENT_SPEC);
//        modLoadingContext.registerConfig(ModConfig.Type.SERVER, ConfigHolder.SERVER_SPEC);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
    }

    public void onCommonSetup(FMLCommonSetupEvent event) {
        Networking.registerMessages();
        CapabilityManager.INSTANCE.register(IEnchants.class, new EnchantsStorage(), Enchants::new);
    }

    public void onClientSetup(FMLClientSetupEvent event) {
        Keys.registerKeys();
    }

}