package com.maciej916.maenchants.common.handler;

import com.maciej916.maenchants.common.registries.ModEnchantments;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;

public class HandlerStoneMending {

    private static final TagKey<Block> FORGE_STONE = BlockTags.create(new ResourceLocation("forge", "stone"));

    public static void handlerBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        BlockState state = event.getState();

        ItemStack stack = player.getItemInHand(player.getUsedItemHand());

        int lvl = stack.getEnchantmentLevel(ModEnchantments.STONE_MENDING.get());
        if (lvl == 0) return;
        if (!state.is(FORGE_STONE)) return;

        switch (lvl) {
            case 1: if (Math.random()> 0.30) return;
            case 2: if (Math.random()> 0.60) return;
            case 3: if (Math.random()> 0.80) return;
        }

        stack.setDamageValue(stack.getDamageValue() - 2);
    }
}