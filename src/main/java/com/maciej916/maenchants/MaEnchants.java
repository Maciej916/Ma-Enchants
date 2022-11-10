package com.maciej916.maenchants;

import com.maciej916.maenchants.common.config.ModConfig;
import com.maciej916.maenchants.common.network.ModNetworking;
import com.maciej916.maenchants.common.proxy.ModProxy;
import com.maciej916.maenchants.common.registries.ModBlocks;
import com.maciej916.maenchants.common.registries.ModEnchantments;
import com.maciej916.maenchants.common.registries.ModMobEffects;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MaEnchants.MODID)
public class MaEnchants {
    public static final String MODID = "maenchants";

    public MaEnchants() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::onCommonSetup);

        ModBlocks.register(modEventBus);
        ModMobEffects.register(modEventBus);
        ModEnchantments.register(modEventBus);


        ModConfig.register();
    }

    public void onCommonSetup(FMLCommonSetupEvent event) {
        ModProxy.init();
        ModNetworking.init();
    }

}