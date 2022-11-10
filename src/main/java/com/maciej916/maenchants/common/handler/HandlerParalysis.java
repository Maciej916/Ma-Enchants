package com.maciej916.maenchants.common.handler;

import com.maciej916.maenchants.common.registries.ModEnchantments;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class HandlerParalysis {

    public static void handlerHurt(LivingHurtEvent event) {
        Entity source = event.getSource().getEntity();
        LivingEntity target = event.getEntity();

        if (!(source instanceof Player player)) return;

        ItemStack stack = player.getItemInHand(player.getUsedItemHand());

        int lvl = stack.getEnchantmentLevel(ModEnchantments.PARALYSIS.get());
        if (lvl == 0) return;

        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, lvl * 20, 100, false, false));
        target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, lvl * 20, 100, false, false));
        target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, lvl * 20, 100, false, false));
    }
}

