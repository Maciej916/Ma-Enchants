package com.maciej916.maenchants.common.handler;

import com.maciej916.maenchants.common.registries.ModEnchantments;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.lang.reflect.Method;

public class HandlerQuickDraw {

    private static int tick = 0;

    public static void handlerPlayerTick(Player player) {
        if (!player.isUsingItem()) {
            tick = 0;
            return;
        }

        ItemStack stack = player.getItemInHand(player.getUsedItemHand());

        int lvl = stack.getEnchantmentLevel(ModEnchantments.QUICK_DRAW.get());
        if (lvl == 0) return;

        switch (lvl) {
            case 1: if (tick % 10 == 0) tickBow(player); break;
            case 2: if (tick % 8 == 0) tickBow(player); break;
            case 3: if (tick % 5 == 0) tickBow(player); break;
        }

        tick++;
        if (tick >= 100) tick = 0;
    }

    private static void tickBow(Player player) {
        try {
            Method method = LivingEntity.class.getDeclaredMethod("updatingUsingItem");
            method.setAccessible(true);
            method.invoke(player);
        } catch (Exception e) {
//            System.out.println("updateActiveHand error " + e.getMessage());
        }
    }

}
