package com.maciej916.maenchants.common.handler;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import java.util.ArrayList;
import java.util.List;

import static com.maciej916.maenchants.common.registries.ModEnchants.TRUE_SHOT;

public class HandlerTrueShot {

    public static List<AbstractArrowEntity> trueShotArrows = new ArrayList<>();

    public static void handlerWorldTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        List<AbstractArrowEntity> removeTrueShot = new ArrayList<>();
        for (AbstractArrowEntity arrow : trueShotArrows) {
            if (arrow.ticksExisted > 600) {
                arrow.remove();
                removeTrueShot.add(arrow);
            }
            if (!arrow.isAlive()) continue;
            arrow.setMotion(arrow.getMotion().scale(1/.99));
            arrow.velocityChanged = true;
        }
        trueShotArrows.removeAll(removeTrueShot);
    }

    public static void handlerSpawn(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof AbstractArrowEntity)) return;
        Entity shooter = ((AbstractArrowEntity) entity).func_234616_v_();
        if (!(shooter instanceof PlayerEntity)) return;
        PlayerEntity player = (PlayerEntity) shooter;

        ItemStack stack = player.getHeldItem(player.getActiveHand());
        if (EnchantmentHelper.getEnchantmentLevel(TRUE_SHOT, stack) == 0) return;

        entity.setNoGravity(true);
        trueShotArrows.add((AbstractArrowEntity)entity);
    }

}
