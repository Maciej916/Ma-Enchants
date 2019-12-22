package com.maciej916.maenchants.handler;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import static com.maciej916.maenchants.init.ModEnchants.BUTCHERING;

public class HandlerButchering {

    public static void handlerAttack(LivingHurtEvent event) {
        Entity source = event.getSource().getTrueSource();
        LivingEntity target = (LivingEntity) event.getEntity();

        if (!(source instanceof PlayerEntity)) return;
        if (target instanceof PlayerEntity || !(target instanceof AnimalEntity)) return;

        PlayerEntity player = (PlayerEntity) source;
        ItemStack stack = player.getHeldItem(player.getActiveHand());
        int lvl = EnchantmentHelper.getEnchantmentLevel(BUTCHERING, stack);
        if (lvl == 0) return;

        float newDamage = event.getAmount() + 0.5f + (float)Math.max(0, lvl - 1) * 0.5F;

        event.setAmount(newDamage);
    }

}