package com.maciej916.maenchants.common.handler;

import com.maciej916.maenchants.common.registries.ModEnchantments;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;

public class HandlerWisdom {

    public static void handlerExpDrop(LivingExperienceDropEvent event) {
        if (event.getEntity() != event.getAttackingPlayer()) return;

        Player player = event.getAttackingPlayer();
        assert player != null;

        ItemStack stack = player.getItemInHand(player.getUsedItemHand());

        int lvl = stack.getEnchantmentLevel(ModEnchantments.WISDOM.get());
        if (lvl == 0) return;

        int originalExp = event.getOriginalExperience();
        int resultExp = (int) (originalExp + originalExp * (lvl * 0.4));
        event.setDroppedExperience(resultExp);
    }

}