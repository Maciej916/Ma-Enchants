package com.maciej916.maenchants;

import com.maciej916.maenchants.capabilities.Enchants;
import com.maciej916.maenchants.capabilities.EnchantsStorage;
import com.maciej916.maenchants.capabilities.IEnchants;
import com.maciej916.maenchants.network.Networking;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MaEnchants.MODID)
public class MaEnchants {
    public static final String MODID = "ma-enchants";

    public MaEnchants() {
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
//        modLoadingContext.registerConfig(ModConfig.Type.CLIENT, ConfigHolder.CLIENT_SPEC);
//        modLoadingContext.registerConfig(ModConfig.Type.SERVER, ConfigHolder.SERVER_SPEC);

        Networking.registerMessages();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
    }

    @SubscribeEvent
    public void onCommonSetup(FMLCommonSetupEvent event) {
        CapabilityManager.INSTANCE.register(IEnchants.class, new EnchantsStorage(), Enchants::new);
    }
}