package com.maciej916.maenchants.common.handler;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;

import static com.maciej916.maenchants.common.registries.ModEnchants.WISDOM;

public class HandlerWisdom {

    public static void handlerExpDrop(LivingExperienceDropEvent event) {
        if (event.getEntity() != event.getAttackingPlayer()) return;

        Player player = event.getAttackingPlayer();

        InteractionHand hand = player.getUsedItemHand();
        if (hand == null) return;

        ItemStack stack = player.getItemInHand(hand);
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(WISDOM, stack);
        if (lvl == 0) return;

        int orginalExp = event.getOriginalExperience();
        int resultExp = (int) (orginalExp + orginalExp * (lvl * 0.4));
        event.setDroppedExperience(resultExp);
    }

}