package com.maciej916.maenchants.common.handler;

import com.maciej916.maenchants.common.registries.ModEnchantments;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;

public class HandlerCurseDeath {

    private static final DamageSource DEATH = new DamageSource("death");
    private static final EquipmentSlot[] SLOTS = {
            EquipmentSlot.HEAD,
            EquipmentSlot.CHEST,
            EquipmentSlot.LEGS,
            EquipmentSlot.FEET,
            EquipmentSlot.MAINHAND
    };

    public static void handlerPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        for (EquipmentSlot slotType : SLOTS) {
            ItemStack stack = player.getItemBySlot(slotType);
            int lvl = stack.getEnchantmentLevel(ModEnchantments.CURSE_DEATH.get());
            if (lvl > 0) {
                player.hurt(DEATH, player.getMaxHealth() / 4);
            }
        }
    }

}