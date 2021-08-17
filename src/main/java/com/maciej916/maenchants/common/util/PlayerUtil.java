package com.maciej916.maenchants.common.util;

import com.maciej916.maenchants.common.capabilities.Capabilities;
import com.maciej916.maenchants.common.capabilities.mod.IModCapability;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;

public class PlayerUtil {

    public static IModCapability getEnchantsCapability(Player player) {
        return player.getCapability(Capabilities.MOD_CAPABILITY, null).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
    }

    @Nullable
    public static IModCapability getAliveEnchantsCapability(Player player) {
        return player.getCapability(Capabilities.MOD_CAPABILITY, null).orElse(null);
    }

}
