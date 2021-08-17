package com.maciej916.maenchants.common.capabilities;

import com.maciej916.maenchants.common.capabilities.basic.DefaultModCapability;
import com.maciej916.maenchants.common.capabilities.mod.IModCapability;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public final class Capabilities {

    @CapabilityInject(IModCapability.class)
    public static Capability<IModCapability> MOD_CAPABILITY = null;

    public static void register() {
        DefaultModCapability.register();
    }

}
