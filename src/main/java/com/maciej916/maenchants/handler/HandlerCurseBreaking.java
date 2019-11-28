package com.maciej916.maenchants.handler;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.event.world.BlockEvent;

import static com.maciej916.maenchants.init.ModEnchants.CURSE_BREAKING;

public class HandlerCurseBreaking {

    public static void handlerBreak(BlockEvent.BreakEvent event) {
        PlayerEntity player = event.getPlayer();

        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        int lvl = EnchantmentHelper.getEnchantmentLevel(CURSE_BREAKING, stack);
        if (lvl == 0) return;

        stack.damageItem(lvl, player, p -> p.sendBreakAnimation(player.getActiveHand()));
    }

}

