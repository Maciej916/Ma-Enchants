package com.maciej916.maenchants.handler;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import static com.maciej916.maenchants.init.ModEnchants.FASTER_ATTACK;

public class HandlerFasterAttack {

    public static void handlerUpdate(PlayerEntity player) {
        int lvl = EnchantmentHelper.getMaxEnchantmentLevel(FASTER_ATTACK, player);
        if (lvl == 0) return;


        int swing = ObfuscationReflectionHelper.getPrivateValue(LivingEntity.class, player, "field_184617_aD");
        swing = new Double(swing * (lvl * 0.1 + 1)).intValue();
        swing = Math.min(swing, 100);
        ObfuscationReflectionHelper.setPrivateValue(LivingEntity.class, player, swing, "field_184617_aD");
    }

}

