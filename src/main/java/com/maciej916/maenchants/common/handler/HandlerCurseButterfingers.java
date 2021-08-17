package com.maciej916.maenchants.common.handler;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;

import static com.maciej916.maenchants.common.registries.ModEnchants.CURSE_BUTTERFINGERS;

public class HandlerCurseButterfingers {

    public static void handlerAttack(LivingHurtEvent event) {
        Entity source = event.getSource().getEntity();
        if (!(source instanceof Player)) return;
        Player player = (Player) source;

        ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(CURSE_BUTTERFINGERS, stack);
        if (lvl == 0) return;

        if (Math.random() > .20) return;
        dropItem(player);
    }

    public static void handlerBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();

        ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(CURSE_BUTTERFINGERS, stack);
        if (lvl == 0) return;

        if (Math.random() > .10) return;
        dropItem(player);
    }

    private static void dropItem(Player player) {
        player.drop(player.getItemInHand(InteractionHand.MAIN_HAND),true);
        player.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
    }
}

