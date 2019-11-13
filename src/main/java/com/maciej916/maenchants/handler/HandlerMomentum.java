package com.maciej916.maenchants.handler;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;

import static com.maciej916.maenchants.init.ModEnchants.MOMENTUM;

public class HandlerMomentum {

    public static void handlerBreak(BlockEvent.BreakEvent event) {
        PlayerEntity player = event.getPlayer();

        if (EnchantmentHelper.getMaxEnchantmentLevel(MOMENTUM, player) == 0) return;

        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        CompoundNBT compound = stack.getOrCreateTag();
        int momentum = compound.getInt("momentum");
        String cachedBlock = compound.getString("block");
        String currentBlock = event.getState().getBlock().toString();
        if (!cachedBlock.equals(currentBlock)) {
            compound.putInt("momentum", 0);
            compound.putString("block", currentBlock);
        } else {
            compound.putInt("momentum", momentum + 1);
        }
    }

    public static void handlerSpeed(PlayerEvent.BreakSpeed event) {
        PlayerEntity player = event.getPlayer();

        if (EnchantmentHelper.getMaxEnchantmentLevel(MOMENTUM, player) == 0) return;

        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        CompoundNBT compound = stack.getOrCreateTag();
        int momentum = compound.getInt("momentum");
        float oldSpeed = event.getOriginalSpeed();
        float newSpeed = oldSpeed + .05f * momentum;
        event.setNewSpeed(newSpeed);
    }

}