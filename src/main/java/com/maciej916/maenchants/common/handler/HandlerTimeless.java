package com.maciej916.maenchants.common.handler;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.item.ItemExpireEvent;

import static com.maciej916.maenchants.common.registries.ModEnchants.TIMELESS;

public class HandlerTimeless {

    public static void handlerItemExpire(ItemExpireEvent event) {
        ItemStack itemStack = event.getEntityItem().getItem();

        int lvl = EnchantmentHelper.getItemEnchantmentLevel(TIMELESS, itemStack);
        if (lvl == 0) return;

        event.setExtraLife(Integer.MAX_VALUE);
        event.setCanceled(true);
    }

}