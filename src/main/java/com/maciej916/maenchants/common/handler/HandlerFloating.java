package com.maciej916.maenchants.common.handler;

import com.maciej916.maenchants.common.registries.ModEnchantments;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class HandlerFloating {

    public static void handlerHurt(LivingHurtEvent event) {
        Entity source = event.getSource().getEntity();
        LivingEntity target = event.getEntity();

        if (!(source instanceof Player player) || source == target) return;

        ItemStack stack = player.getItemInHand(player.getUsedItemHand());

        int lvl = stack.getEnchantmentLevel(ModEnchantments.FLOATING.get());
        if (lvl == 0) return;

        target.addEffect(new MobEffectInstance(MobEffects.LEVITATION, lvl * 10, 1, false, false));
    }
}

