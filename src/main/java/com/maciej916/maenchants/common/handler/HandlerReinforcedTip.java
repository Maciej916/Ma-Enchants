package com.maciej916.maenchants.common.handler;

import com.maciej916.maenchants.common.registries.ModEnchantments;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class HandlerReinforcedTip {

    private static final TagKey<Block> FORGE_OBSIDIAN = BlockTags.create(new ResourceLocation("forge", "obsidian"));

    public static void handlerSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        BlockState state = event.getState();

        ItemStack stack = player.getItemInHand(player.getUsedItemHand());

        int lvl = stack.getEnchantmentLevel(ModEnchantments.REINFORCED_TIP.get());
        if (lvl == 0) return;

        if (!state.is(FORGE_OBSIDIAN)) return;

        float oldSpeed = event.getOriginalSpeed();
        event.setNewSpeed(oldSpeed * lvl + 1);
    }

}
