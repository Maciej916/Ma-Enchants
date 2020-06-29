package com.maciej916.maenchants.common.handler;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.event.world.BlockEvent;

import static com.maciej916.maenchants.common.registries.ModEnchants.STONE_MENDING;

public class HandlerStoneMending {

    public static void handlerBreak(BlockEvent.BreakEvent event) {
        PlayerEntity player = event.getPlayer();
        BlockState state = event.getState();

        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        int lvl = EnchantmentHelper.getEnchantmentLevel(STONE_MENDING, stack);
        if (lvl == 0) return;

        if (state.getBlock() != Blocks.STONE) return;

        switch (lvl) {
            case 1: if (Math.random()> 0.30) return;
            case 2: if (Math.random()> 0.60) return;
            case 3: if (Math.random()> 0.80) return;
        }

        stack.setDamage(stack.getDamage() - 2);
    }

}