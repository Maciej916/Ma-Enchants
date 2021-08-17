package com.maciej916.maenchants.common.handler;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import static com.maciej916.maenchants.common.registries.ModEnchants.FASTER_ATTACK;

public class HandlerFasterAttack {

    public static void handlerPlayerTick(Player player) {

        InteractionHand hand = player.getUsedItemHand();
        if (hand == null) return;

        ItemStack stack = player.getItemInHand(hand);

        int lvl = EnchantmentHelper.getItemEnchantmentLevel(FASTER_ATTACK, stack);
        if (lvl == 0) return;

//        int swing = ObfuscationReflectionHelper.getPrivateValue(LivingEntity.class, player, "field_184617_aD");
//        swing = Double.valueOf(swing * (lvl * 0.1 + 1)).intValue();
//        swing = Math.min(swing, 100);
//        ObfuscationReflectionHelper.setPrivateValue(LivingEntity.class, player, swing, "field_184617_aD");
    }

}

