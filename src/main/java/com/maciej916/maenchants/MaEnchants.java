package com.maciej916.maenchants;

import com.maciej916.maenchants.common.capabilities.Capabilities;
import com.maciej916.maenchants.common.config.ConfigHolder;
import com.maciej916.maenchants.common.network.Networking;
import com.maciej916.maenchants.common.proxy.ClientProxy;
import com.maciej916.maenchants.common.proxy.IProxy;
import com.maciej916.maenchants.common.proxy.ServerProxy;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MaEnchants.MODID)
public class MaEnchants {
    public static final String MODID = "maenchants";
    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public MaEnchants() {
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
        modLoadingContext.registerConfig(ModConfig.Type.COMMON, ConfigHolder.COMMON_SPEC);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
    }

    public void onCommonSetup(FMLCommonSetupEvent event) {
        Capabilities.register();
        Networking.registerMessages();
        proxy.init();
    }

}