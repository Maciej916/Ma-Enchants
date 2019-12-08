package com.maciej916.maenchants.utils;

import com.maciej916.maenchants.capabilities.EnchantsProvider;
import com.maciej916.maenchants.capabilities.IEnchants;
import net.minecraft.entity.player.PlayerEntity;

import javax.annotation.Nullable;

public class PlayerUtil {

    public static IEnchants getEnchantsCapability(PlayerEntity player) {
        return player.getCapability(EnchantsProvider.ENCHANTS_CAPABILITY, null).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
    }

    @Nullable
    public static IEnchants getAliveEnchantsCapability(PlayerEntity player) {
        return player.getCapability(EnchantsProvider.ENCHANTS_CAPABILITY, null).orElse(null);
    }

}
