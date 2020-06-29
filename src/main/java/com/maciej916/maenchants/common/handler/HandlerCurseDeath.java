package com.maciej916.maenchants.common.handler;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraftforge.event.TickEvent;

import static com.maciej916.maenchants.common.registries.ModEnchants.CURSE_DEATH;

public class HandlerCurseDeath {

    private static final DamageSource DEATH = new DamageSource("death");
    private static EquipmentSlotType armour[] = {
        EquipmentSlotType.HEAD,
        EquipmentSlotType.CHEST,
        EquipmentSlotType.LEGS,
        EquipmentSlotType.FEET
    };

    public static void handlerPlayerTick(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;

        for (EquipmentSlotType slotType : armour) {
            ItemStack stack = player.getItemStackFromSlot(slotType);
            int lvl = EnchantmentHelper.getEnchantmentLevel(CURSE_DEATH, stack);
            if (lvl == 0) continue;
            player.attackEntityFrom(DEATH, player.getMaxHealth() / 4);
        }

        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        int lvl = EnchantmentHelper.getEnchantmentLevel(CURSE_DEATH, stack);
        if (lvl == 0) return;

        player.attackEntityFrom(DEATH, player.getMaxHealth() / 4);
    }

}