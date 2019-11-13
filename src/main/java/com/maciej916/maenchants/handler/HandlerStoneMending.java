package com.maciej916.maenchants.handler;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.event.world.BlockEvent;

import static com.maciej916.maenchants.init.ModEnchants.STONE_MENDING;

public class HandlerStoneMending {

    public static void handlerBreak(BlockEvent.BreakEvent event) {
        PlayerEntity player = event.getPlayer();
        BlockState state = event.getState();

        int lvl = EnchantmentHelper.getMaxEnchantmentLevel(STONE_MENDING, player);
        if (lvl == 0) return;
        if (state.getBlock() != Blocks.STONE) return;

        switch (lvl) {
            case 1: if (Math.random()> 0.30) return;
            case 2: if (Math.random()> 0.60) return;
            case 3: if (Math.random()> 0.80) return;
        }

        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        stack.setDamage(stack.getDamage() - 2);
    }

}