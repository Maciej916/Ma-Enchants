package com.maciej916.maenchants.common.handler;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.world.BlockEvent;

import static com.maciej916.maenchants.common.registries.ModEnchants.STONE_MENDING;

public class HandlerStoneMending {

    public static void handlerBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        BlockState state = event.getState();

        InteractionHand hand = player.getUsedItemHand();
        if (hand == null) return;

        ItemStack stack = player.getItemInHand(hand);
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(STONE_MENDING, stack);
        if (lvl == 0) return;

        ResourceLocation stoneTagID = new ResourceLocation("forge", "stone");
        if (!state.getBlock().getTags().contains(stoneTagID)) return;

        switch (lvl) {
            case 1: if (Math.random()> 0.30) return;
            case 2: if (Math.random()> 0.60) return;
            case 3: if (Math.random()> 0.80) return;
        }

        stack.setDamageValue(stack.getDamageValue() - 2);
    }
}