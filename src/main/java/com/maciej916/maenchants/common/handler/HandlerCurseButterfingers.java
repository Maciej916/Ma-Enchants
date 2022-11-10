package com.maciej916.maenchants.common.handler;

import com.maciej916.maenchants.common.registries.ModEnchantments;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.level.BlockEvent;

public class HandlerCurseButterfingers {

    public static void handlerHurt(LivingHurtEvent event) {
        Entity source = event.getSource().getEntity();
        if (!(source instanceof Player player)) return;

        ItemStack stack = player.getItemInHand(player.getUsedItemHand());

        int lvl = stack.getEnchantmentLevel(ModEnchantments.CURSE_BUTTERFINGERS.get());
        if (lvl == 0) return;

        if (Math.random() > .20) return;
        dropItem(player);
    }

    public static void handlerBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack stack = player.getItemInHand(player.getUsedItemHand());

        int lvl = stack.getEnchantmentLevel(ModEnchantments.CURSE_BUTTERFINGERS.get());
        if (lvl == 0) return;

        if (Math.random() > .10) return;
        dropItem(player);
    }

    private static void dropItem(Player player) {
        player.drop(player.getItemInHand(InteractionHand.MAIN_HAND),true);
        player.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
    }
}

