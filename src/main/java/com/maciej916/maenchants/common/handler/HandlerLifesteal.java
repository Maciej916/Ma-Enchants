package com.maciej916.maenchants.common.handler;

import com.maciej916.maenchants.common.registries.ModEnchantments;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class HandlerLifesteal {

    public static void handlerHurt(LivingHurtEvent event) {
        Entity source = event.getSource().getEntity();
        LivingEntity entity = event.getEntity();

        if (!(source instanceof Player player)) return;

        ItemStack stack = player.getItemInHand(player.getUsedItemHand());

        int lvl = stack.getEnchantmentLevel(ModEnchantments.LIFESTEAL.get());
        if (lvl == 0) return;

        float damage = event.getAmount();
        entity.hurt(DamageSource.GENERIC, damage*1.5f);
        player.heal(damage);
    }

}