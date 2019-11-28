package com.maciej916.maenchants.handler;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.player.PlayerEvent;

import static com.maciej916.maenchants.init.ModEnchants.REINFORCED_TIP;

public class HandlerReinforcedTip {

    public static void handlerSpeed(PlayerEvent.BreakSpeed event) {
        PlayerEntity player = event.getPlayer();
        BlockState state = event.getState();

        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        int lvl = EnchantmentHelper.getEnchantmentLevel(REINFORCED_TIP, stack);
        if (lvl == 0) return;

        if (state.getBlock() != Blocks.OBSIDIAN) return;

        float oldSpeed = event.getOriginalSpeed();
        event.setNewSpeed(oldSpeed * lvl + 1);
    }

}
