package com.maciej916.maenchants.handler;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;

import static com.maciej916.maenchants.init.ModEnchants.WISDOM;

public class HandlerWisdom {

    public static void handlerExpDrop(LivingExperienceDropEvent event) {
        int lvl = EnchantmentHelper.getMaxEnchantmentLevel(WISDOM, event.getAttackingPlayer());
        if (lvl == 0) return;
        int orginalExp = event.getOriginalExperience();
        int resultExp = (int) (orginalExp + orginalExp * (lvl * 0.2));
        event.setDroppedExperience(resultExp);
    }

}