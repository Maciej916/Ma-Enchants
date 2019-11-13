package com.maciej916.maenchants.handler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.world.BlockEvent;

import static com.maciej916.maenchants.init.ModEnchants.LUMBERJACK;

public class HandlerLumberjack {

    public static void handlerBreak(BlockEvent.BreakEvent event) {
        PlayerEntity player = event.getPlayer();

        if (EnchantmentHelper.getMaxEnchantmentLevel(LUMBERJACK, player) == 0) return;

        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        BlockState state = event.getState();
        Block block = state.getBlock();

        if (!block.isIn(BlockTags.LOGS)) return;

        BlockPos pos = event.getPos();
        while (block.isIn(BlockTags.LOGS)) {
            pos = pos.up();
            state = player.world.getBlockState(pos);
            block = state.getBlock();
            if (block.isIn(BlockTags.LOGS)) {
                stack.damageItem(1, player, playerEntity -> playerEntity.sendBreakAnimation(player.getActiveHand()));
                player.world.destroyBlock(pos,true);
            }
        }
    }

}