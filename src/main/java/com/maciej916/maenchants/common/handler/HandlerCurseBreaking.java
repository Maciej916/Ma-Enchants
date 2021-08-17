package com.maciej916.maenchants.common.handler;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;

import java.util.Objects;
import java.util.Random;

import static com.maciej916.maenchants.common.registries.ModEnchants.CURSE_BREAKING;

public class HandlerCurseBreaking {

    public static void handlerBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();

        InteractionHand hand = player.getUsedItemHand();
        if (hand == null) return;

        ItemStack stack = player.getItemInHand(hand);
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(CURSE_BREAKING, stack);
        if (lvl == 0) return;
        Random rand = new Random();
        damageStack(player, stack, rand, lvl);
    }

    public static void handlerAttack(LivingHurtEvent event) {
        Entity source = event.getSource().getEntity();
        Entity target = event.getEntity();

        if (source == null || target == null) return;

        Random rand = new Random();

        if (source instanceof Player) {
            Player player = (Player) source;

            ItemStack stack_hand = player.getItemBySlot(EquipmentSlot.MAINHAND);
            int lvl_hand = EnchantmentHelper.getItemEnchantmentLevel(CURSE_BREAKING, stack_hand);
            if (lvl_hand > 0) {
                damageStack(player, stack_hand, rand, lvl_hand);
            }
        }

        if (target instanceof Player) {
            Player player = (Player) target;

            ItemStack stack_head = player.getItemBySlot(EquipmentSlot.HEAD);
            int lvl_head = EnchantmentHelper.getItemEnchantmentLevel(CURSE_BREAKING, stack_head);
            if (lvl_head > 0 && rand.nextFloat() < 0.6F) {
                damageStack(player, stack_head, rand, lvl_head);
            }

            ItemStack stack_chest = player.getItemBySlot(EquipmentSlot.CHEST);
            int lvl_chest = EnchantmentHelper.getItemEnchantmentLevel(CURSE_BREAKING, stack_chest);
            if (lvl_chest > 0 && rand.nextFloat() < 0.6F) {
                damageStack(player, stack_chest, rand, lvl_chest);
            }

            ItemStack stack_legs = player.getItemBySlot(EquipmentSlot.LEGS);
            int lvl_legs = EnchantmentHelper.getItemEnchantmentLevel(CURSE_BREAKING, stack_legs);
            if (lvl_legs > 0 && rand.nextFloat() < 0.6F) {
                damageStack(player, stack_legs, rand, lvl_legs);
            }

            ItemStack stack_feet = player.getItemBySlot(EquipmentSlot.FEET);
            int lvl_feet = EnchantmentHelper.getItemEnchantmentLevel(CURSE_BREAKING, stack_feet);
            if (lvl_feet > 0 && rand.nextFloat() < 0.6F) {
                damageStack(player, stack_feet, rand, lvl_feet);
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

