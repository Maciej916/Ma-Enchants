package com.maciej916.maenchants.common.util;

import com.maciej916.maenchants.common.capabilities.player.IPlayerCapability;
import com.maciej916.maenchants.common.registries.ModCapabilities;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;

public class PlayerUtil {

    public static IPlayerCapability getEnchantsCapability(Player player) {
        return player.getCapability(ModCapabilities.PLAYER_CAPABILITY, null).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
    }

    @Nullable
    public static IPlayerCapability getAliveEnchantsCapability(Player player) {
        return player.getCapability(ModCapabilities.PLAYER_CAPABILITY, null).orElse(null);
    }

}
