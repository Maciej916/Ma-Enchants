package com.maciej916.maenchants.common.handler;

import com.maciej916.maenchants.common.registries.ModEnchantments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class HandlerButchering {

    public static void handlerHurt(LivingHurtEvent event) {
        Entity source = event.getSource().getEntity();
        LivingEntity target = event.getEntity();

        if (!(source instanceof Player player) || !(target instanceof Animal)) return;

        ItemStack stack = player.getItemInHand(player.getUsedItemHand());

        int lvl = stack.getEnchantmentLevel(ModEnchantments.BUTCHERING.get());
        if (lvl > 0) {
            float newDamage = event.getAmount() + 0.5f + (float)Math.max(0, lvl - 1) * 0.5F;
            event.setAmount(newDamage);
        }
    }

}