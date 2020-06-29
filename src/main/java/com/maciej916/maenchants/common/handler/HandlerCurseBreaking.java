package com.maciej916.maenchants.common.handler;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;

import java.util.Objects;
import java.util.Random;

import static com.maciej916.maenchants.common.registries.ModEnchants.CURSE_BREAKING;

public class HandlerCurseBreaking {

    public static void handlerBreak(BlockEvent.BreakEvent event) {
        PlayerEntity player = event.getPlayer();

        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        int lvl = EnchantmentHelper.getEnchantmentLevel(CURSE_BREAKING, stack);
        if (lvl == 0) return;
        Random rand = new Random();
        damageStack(player, stack, rand, lvl);
    }

    public static void handlerAttack(LivingHurtEvent event) {
        Entity source = event.getSource().getTrueSource();
        Entity target = event.getEntity();

        Random rand = new Random();

        if (source instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) source;

            ItemStack stack_hand = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
            int lvl_hand = EnchantmentHelper.getEnchantmentLevel(CURSE_BREAKING, stack_hand);
            if (lvl_hand > 0) {
                damageStack(player, stack_hand, rand, lvl_hand);
            }
        }

        if (target instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) target;

            ItemStack stack_head = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
            int lvl_head = EnchantmentHelper.getEnchantmentLevel(CURSE_BREAKING, stack_head);
            if (lvl_head > 0 && rand.nextFloat() < 0.6F) {
                damageStack(player, stack_head, rand, lvl_head);
            }

            ItemStack stack_chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
            int lvl_chest = EnchantmentHelper.getEnchantmentLevel(CURSE_BREAKING, stack_chest);
            if (lvl_chest > 0 && rand.nextFloat() < 0.6F) {
                damageStack(player, stack_chest, rand, lvl_chest);
            }

            ItemStack stack_legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS);
            int lvl_legs = EnchantmentHelper.getEnchantmentLevel(CURSE_BREAKING, stack_legs);
            if (lvl_legs > 0 && rand.nextFloat() < 0.6F) {
                damageStack(player, stack_legs, rand, lvl_legs);
            }

            ItemStack stack_feet = player.getItemStackFromSlot(EquipmentSlotType.FEET);
            int lvl_feet = EnchantmentHelper.getEnchantmentLevel(CURSE_BREAKING, stack_feet);
            if (lvl_feet > 0 && rand.nextFloat() < 0.6F) {
                damageStack(player, stack_feet, rand, lvl_feet);
            }
        }
    }

    private static void damageStack(PlayerEntity player, ItemStack stack, Random rand, int lvl) {
        if (rand.nextInt(lvl + 1) > 0) {
            stack.damageItem(lvl, player, p -> p.sendBreakAnimation(Objects.requireNonNull(stack.getEquipmentSlot())));
        }
    }
}

