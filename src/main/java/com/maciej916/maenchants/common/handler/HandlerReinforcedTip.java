package com.maciej916.maenchants.common.handler;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerEvent;

import static com.maciej916.maenchants.common.registries.ModEnchants.REINFORCED_TIP;

public class HandlerReinforcedTip {

    public static void handlerSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getPlayer();
        BlockState state = event.getState();

        InteractionHand hand = player.getUsedItemHand();
        if (hand == null) return;

        ItemStack stack = player.getItemInHand(hand);
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(REINFORCED_TIP, stack);
        if (lvl == 0) return;

        if (state.getBlock() != Blocks.OBSIDIAN) return;

        float oldSpeed = event.getOriginalSpeed();
        event.setNewSpeed(oldSpeed * lvl + 1);
    }

}
