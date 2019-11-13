package com.maciej916.maenchants.handler;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;

import static com.maciej916.maenchants.init.ModEnchants.REINFORCED_TIP;

public class HandlerReinforcedTip {

    public static void handlerSpeed(PlayerEvent.BreakSpeed event) {
        PlayerEntity player = event.getPlayer();
        BlockState state = event.getState();

        int lvl = EnchantmentHelper.getMaxEnchantmentLevel(REINFORCED_TIP, player);
        if (lvl == 0) return;
        if (state.getBlock() != Blocks.OBSIDIAN) return;

        float oldSpeed = event.getOriginalSpeed();
        event.setNewSpeed(oldSpeed * lvl + 1);
    }

}
