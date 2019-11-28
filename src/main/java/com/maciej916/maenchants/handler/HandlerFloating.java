package com.maciej916.maenchants.handler;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import static com.maciej916.maenchants.init.ModEnchants.FLOATING;

public class HandlerFloating {

    public static void handlerAttack(LivingHurtEvent event) {
        Entity source = event.getSource().getTrueSource();
        LivingEntity target = (LivingEntity) event.getEntity();

        if (!(source instanceof PlayerEntity)) return;

        PlayerEntity player = (PlayerEntity) source;
        ItemStack stack = player.getHeldItem(player.getActiveHand());
        int lvl = EnchantmentHelper.getEnchantmentLevel(FLOATING, stack);
        if (lvl == 0) return;

        target.addPotionEffect(new EffectInstance(Effects.LEVITATION, lvl * 10, 1, false, false));
    }

}

