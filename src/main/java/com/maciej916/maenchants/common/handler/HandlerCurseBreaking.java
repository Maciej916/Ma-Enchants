package com.maciej916.maenchants.common.handler;

import com.maciej916.maenchants.common.registries.ModEnchantments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.level.BlockEvent;

import java.util.Objects;
import java.util.Random;

public class HandlerCurseBreaking {

    public static void handlerBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack stack = player.getItemInHand(player.getUsedItemHand());

        int lvl = stack.getEnchantmentLevel(ModEnchantments.CURSE_BREAKING.get());
        if (lvl == 0) return;

        Random rand = new Random();
        damageStack(player, stack, rand, lvl);
    }

    public static void handlerHurt(LivingHurtEvent event) {
        Entity source = event.getSource().getEntity();
        Entity target = event.getEntity();

        if (source == null || target == null) return;

        Random rand = new Random();

        if (source instanceof Player player) {
            ItemStack stackInHand = player.getItemInHand(player.getUsedItemHand());
            int lvlHand = stackInHand.getEnchantmentLevel(ModEnchantments.CURSE_BREAKING.get());
            if (lvlHand > 0) {
                damageStack(player, stackInHand, rand, lvlHand);
            }
        }

        if (target instanceof Player player) {
            ItemStack stackOnHead = player.getItemBySlot(EquipmentSlot.HEAD);
            int lvlHead = stackOnHead.getEnchantmentLevel(ModEnchantments.CURSE_BREAKING.get());
            if (lvlHead > 0 && rand.nextFloat() < 0.6F) {
                damageStack(player, stackOnHead, rand, lvlHead);
            }

            ItemStack stackOnChest = player.getItemBySlot(EquipmentSlot.CHEST);
            int lvlChest = stackOnChest.getEnchantmentLevel(ModEnchantments.CURSE_BREAKING.get());
            if (lvlChest > 0 && rand.nextFloat() < 0.6F) {
                damageStack(player, stackOnChest, rand, lvlChest);
            }

            ItemStack stackOnLegs = player.getItemBySlot(EquipmentSlot.LEGS);
            int lvlLegs = stackOnLegs.getEnchantmentLevel(ModEnchantments.CURSE_BREAKING.get());
            if (lvlLegs > 0 && rand.nextFloat() < 0.6F) {
                damageStack(player, stackOnLegs, rand, lvlLegs);
            }

            ItemStack stackOnFeet = player.getItemBySlot(EquipmentSlot.FEET);
            int lvlFeet = stackOnFeet.getEnchantmentLevel(ModEnchantments.CURSE_BREAKING.get());
            if (lvlFeet > 0 && rand.nextFloat() < 0.6F) {
                damageStack(player, stackOnFeet, rand, lvlFeet);
            }
        }
    }

    private static void damageStack(Player player, ItemStack stack, Random rand, int lvl) {
        try {
            if (rand.nextInt(lvl + 1) > 0) {
                stack.hurtAndBreak(lvl, player, p -> p.broadcastBreakEvent(Objects.requireNonNull(stack.getEquipmentSlot())));
            }
        } catch (Exception ignored) {

        }
    }
}

