package com.maciej916.maenchants.common.handler;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import static com.maciej916.maenchants.common.registries.ModEnchants.LIFESTEAL;

public class HandlerLifesteal {

    public static void handlerAttack(LivingHurtEvent event) {
        Entity source = event.getSource().getEntity();

        if (!(source instanceof Player player)) return;

        LivingEntity entity = event.getEntityLiving();

        InteractionHand hand = player.getUsedItemHand();
        if (hand == null) return;

        ItemStack stack = player.getItemInHand(hand);
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(LIFESTEAL, stack);
        if (lvl == 0) return;

        float damage = event.getAmount();
        entity.hurt(DamageSource.GENERIC, damage*1.5f);
        player.heal(damage);
    }

}