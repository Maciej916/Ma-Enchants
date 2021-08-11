package com.maciej916.maenchants.common.handler;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import static com.maciej916.maenchants.common.registries.ModEnchants.FASTER_ATTACK;

public class HandlerFasterAttack {

    public static void handlerPlayerTick(PlayerEntity player) {
        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        int lvl = EnchantmentHelper.getEnchantmentLevel(FASTER_ATTACK, stack);
        if (lvl == 0) return;

        int swing = ObfuscationReflectionHelper.getPrivateValue(LivingEntity.class, player, "field_184617_aD");
        swing = Double.valueOf(swing * (lvl * 0.1 + 1)).intValue();
        swing = Math.min(swing, 100);
        ObfuscationReflectionHelper.setPrivateValue(LivingEntity.class, player, swing, "field_184617_aD");
    }

}

