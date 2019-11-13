package com.maciej916.maenchants.handler;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import static com.maciej916.maenchants.init.ModEnchants.LIFESTEAL;

public class HandlerLifesteal {

    public static void handlerAttack(LivingHurtEvent event) {
        Entity source = event.getSource().getTrueSource();

        if (!(source instanceof PlayerEntity)) return;

        PlayerEntity player = (PlayerEntity) source;
        LivingEntity entity = event.getEntityLiving();

        int lvl = EnchantmentHelper.getMaxEnchantmentLevel(LIFESTEAL, player);
        if (lvl == 0) return;

        float damage = event.getAmount();
        entity.attackEntityFrom(DamageSource.GENERIC, damage*1.5f);
        player.heal(damage);
    }

}