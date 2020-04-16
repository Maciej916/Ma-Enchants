package com.maciej916.maenchants.handler;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;

import static com.maciej916.maenchants.init.ModEnchants.LIFESTEAL;
import static com.maciej916.maenchants.init.ModEnchants.WISDOM;

public class HandlerWisdom {

    public static void handlerExpDrop(LivingExperienceDropEvent event) {
        if (event.getEntity() != event.getAttackingPlayer()) return;

        ItemStack stack = event.getAttackingPlayer().getHeldItem(Hand.MAIN_HAND);
        int lvl = EnchantmentHelper.getEnchantmentLevel(WISDOM, stack);
        if (lvl == 0) return;

        int orginalExp = event.getOriginalExperience();
        int resultExp = (int) (orginalExp + orginalExp * (lvl * 0.4));
        event.setDroppedExperience(resultExp);
    }

}