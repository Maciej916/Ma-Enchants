package com.maciej916.maenchants.common.handler;

import com.maciej916.maenchants.common.registries.ModEnchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.item.ItemExpireEvent;

public class HandlerTimeless {

    public static void handlerItemExpire(ItemExpireEvent event) {
        ItemStack stack = event.getEntity().getItem();

        int lvl = stack.getEnchantmentLevel(ModEnchantments.TIMELESS.get());
        if (lvl == 0) return;

        event.setExtraLife(Integer.MAX_VALUE);
        event.setCanceled(true);
    }

}