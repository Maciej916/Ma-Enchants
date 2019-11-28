package com.maciej916.maenchants.handler;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.lang.reflect.Method;

import static com.maciej916.maenchants.init.ModEnchants.QUICK_DRAW;

public class HandlerQuickDraw {

    private static int tick = 0;

    public static void handlerPlayerTick(PlayerEntity player) {
        if (!player.isHandActive()) {
            tick = 0;
            return;
        }

        ItemStack stack = player.getHeldItem(player.getActiveHand());
        int lvl = EnchantmentHelper.getEnchantmentLevel(QUICK_DRAW, stack);
        if (lvl == 0) return;

        switch (lvl) {
            case 1: if (tick % 10 == 0) tickBow(player); break;
            case 2: if (tick % 8 == 0) tickBow(player); break;
            case 3: if (tick % 5 == 0) tickBow(player); break;
        }

        tick++;
        if (tick >= 100) tick = 0;
    }

    private static void tickBow(PlayerEntity player) {
        try {
            Method method = LivingEntity.class.getDeclaredMethod("updateActiveHand");
            method.setAccessible(true);
            method.invoke(player);
        } catch (Exception e) {
            System.out.println("updateActiveHand error " + e.getMessage());
        }
    }

}
