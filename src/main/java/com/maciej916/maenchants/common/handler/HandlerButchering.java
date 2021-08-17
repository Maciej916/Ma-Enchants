package com.maciej916.maenchants.common.handler;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import static com.maciej916.maenchants.common.registries.ModEnchants.BUTCHERING;

public class HandlerButchering {

    public static void handlerAttack(LivingHurtEvent event) {
        Entity source = event.getSource().getEntity();
        LivingEntity target = (LivingEntity) event.getEntity();

        if (!(source instanceof Player player) || !(target instanceof Animal)) return;

        InteractionHand hand = player.getUsedItemHand();
        if (hand == null) return;

        ItemStack stack = player.getItemInHand(hand);
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(BUTCHERING, stack);
        if (lvl == 0) return;

        float newDamage = event.getAmount() + 0.5f + (float)Math.max(0, lvl - 1) * 0.5F;

        event.setAmount(newDamage);
    }

}