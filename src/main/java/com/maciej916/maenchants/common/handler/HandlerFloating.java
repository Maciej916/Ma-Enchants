package com.maciej916.maenchants.common.handler;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import static com.maciej916.maenchants.common.registries.ModEnchants.FLOATING;

public class HandlerFloating {

    public static void handlerAttack(LivingHurtEvent event) {
        Entity source = event.getSource().getEntity();
        LivingEntity target = (LivingEntity) event.getEntity();

        if (!(source instanceof Player player) || source == target) return;

        InteractionHand hand = player.getUsedItemHand();
        if (hand == null) return;

        ItemStack stack = player.getItemInHand(hand);
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(FLOATING, stack);
        if (lvl == 0) return;

        target.addEffect(new MobEffectInstance(MobEffects.LEVITATION, lvl * 10, 1, false, false));
    }
}

