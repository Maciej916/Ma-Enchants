package com.maciej916.maenchants.common.handler;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.TickEvent;

import static com.maciej916.maenchants.common.registries.ModEnchants.CURSE_DEATH;

public class HandlerCurseDeath {

    private static final DamageSource DEATH = new DamageSource("death");
    private static EquipmentSlot armour[] = {
            EquipmentSlot.HEAD,
            EquipmentSlot.CHEST,
            EquipmentSlot.LEGS,
            EquipmentSlot.FEET
    };

    public static void handlerPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        for (EquipmentSlot slotType : armour) {
            ItemStack stack = player.getItemBySlot(slotType);
            int lvl = EnchantmentHelper.getItemEnchantmentLevel(CURSE_DEATH, stack);
            if (lvl == 0) continue;
            player.hurt(DEATH, player.getMaxHealth() / 4);
        }

        ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(CURSE_DEATH, stack);
        if (lvl == 0) return;

        player.hurt(DEATH, player.getMaxHealth() / 4);
    }

}