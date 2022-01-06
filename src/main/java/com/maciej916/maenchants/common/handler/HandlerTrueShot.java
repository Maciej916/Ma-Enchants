package com.maciej916.maenchants.common.handler;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import java.util.ArrayList;
import java.util.List;

import static com.maciej916.maenchants.common.registries.ModEnchants.TRUE_SHOT;

public class HandlerTrueShot {

//    public static List<AbstractArrow> trueShotArrows = new ArrayList<>();

//    public static void handlerWorldTick(TickEvent.ServerTickEvent event) {
//        if (event.phase != TickEvent.Phase.END) return;
//
//
//        System.out.println("trueShotArrows");
//        System.out.println(trueShotArrows.size());
//
//        List<AbstractArrow> removeTrueShot = new ArrayList<>();
//        for (AbstractArrow arrow : trueShotArrows) {
//            if (arrow.tickCount > 600) {
//                arrow.discard();
//                removeTrueShot.add(arrow);
//            }
//            if (!arrow.isAlive()) continue;
//            arrow.setDeltaMovement(arrow.getDeltaMovement().scale(1/.99));
//        }
//        trueShotArrows.removeAll(removeTrueShot);
//    }
//
//    public static void handlerSpawn(EntityJoinWorldEvent event) {
//        Entity entity = event.getEntity();
//        if (!(entity instanceof AbstractArrow)) return;
//        Entity shooter = ((AbstractArrow) entity).getOwner();
//        if (!(shooter instanceof Player player)) return;
//
//        InteractionHand hand = player.getUsedItemHand();
//        if (hand == null) return;
//
//        ItemStack stack = player.getItemInHand(hand);
//        if (EnchantmentHelper.getItemEnchantmentLevel(TRUE_SHOT, stack) == 0) return;
//
//        entity.setNoGravity(true);
//        trueShotArrows.add((AbstractArrow)entity);
//    }

}
