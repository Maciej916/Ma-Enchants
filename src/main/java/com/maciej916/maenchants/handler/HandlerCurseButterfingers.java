package com.maciej916.maenchants.handler;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;

import static com.maciej916.maenchants.init.ModEnchants.CURSE_BUTTERFINGERS;

public class HandlerCurseButterfingers {

    public static void handlerAttack(LivingHurtEvent event) {
        Entity source = event.getSource().getTrueSource();
        if (!(source instanceof PlayerEntity)) return;
        PlayerEntity player = (PlayerEntity) source;

        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        int lvl = EnchantmentHelper.getEnchantmentLevel(CURSE_BUTTERFINGERS, stack);
        if (lvl == 0) return;

        if (Math.random() > .20) return;
        dropItem(player);
    }

    public static void handlerBreak(BlockEvent.BreakEvent event) {
        PlayerEntity player = event.getPlayer();

        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        int lvl = EnchantmentHelper.getEnchantmentLevel(CURSE_BUTTERFINGERS, stack);
        if (lvl == 0) return;

        if (Math.random() > .10) return;
        dropItem(player);
    }

    private static void dropItem(PlayerEntity player) {
        player.dropItem(player.getItemStackFromSlot(EquipmentSlotType.MAINHAND),true);
        player.setItemStackToSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
    }
}

