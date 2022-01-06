package com.maciej916.maenchants.common.registries;

import com.maciej916.maenchants.common.capabilities.level.ILevelCapability;
import com.maciej916.maenchants.common.capabilities.player.IPlayerCapability;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModCapabilities {

    public static final Capability<ILevelCapability> LEVEL_CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});
    public static final Capability<IPlayerCapability> PLAYER_CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});

    @SubscribeEvent
    public void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(ILevelCapability.class);
        event.register(IPlayerCapability.class);
    }
}
