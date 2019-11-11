package com.maciej916.maenchants.utils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class EnchantUtils {


    public static ItemStack getBowInHand(PlayerEntity player) {
        ItemStack leftHand = player.getHeldItem(Hand.MAIN_HAND);
        if (leftHand.getItem() instanceof BowItem) return leftHand;
        ItemStack rightHand = player.getHeldItem(Hand.OFF_HAND);
        if (rightHand.getItem() instanceof BowItem) return rightHand;
        return null;
    }



}
