package com.maciej916.maenchants;

import com.maciej916.maenchants.capabilities.Enchants;
import com.maciej916.maenchants.capabilities.EnchantsStorage;
import com.maciej916.maenchants.capabilities.IEnchants;
import com.maciej916.maenchants.client.Keys;
import com.maciej916.maenchants.config.ConfigHolder;
import com.maciej916.maenchants.network.Networking;
import com.maciej916.maenchants.proxy.ClientProxy;
import com.maciej916.maenchants.proxy.IProxy;
import com.maciej916.maenchants.proxy.ServerProxy;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MaEnchants.MODID)
public class MaEnchants {
    public static final String MODID = "ma-enchants";
    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

    public MaEnchants() {
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
        modLoadingContext.registerConfig(ModConfig.Type.SERVER, ConfigHolder.SERVER_SPEC);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
    }

    public void onCommonSetup(FMLCommonSetupEvent event) {
        Networking.registerMessages();
        proxy.init();
        CapabilityManager.INSTANCE.register(IEnchants.class, new EnchantsStorage(), Enchants::new);
    }

}