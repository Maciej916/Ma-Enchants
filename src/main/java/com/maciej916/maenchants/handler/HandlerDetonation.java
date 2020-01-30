package com.maciej916.maenchants.handler;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Explosion;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import static com.maciej916.maenchants.init.ModEnchants.DETONATION;

public class HandlerDetonation {

    public static void handlerAttack(LivingHurtEvent event) {
        Entity source = event.getSource().getTrueSource();
        LivingEntity target = (LivingEntity) event.getEntity();

        if (!(source instanceof PlayerEntity)) return;

        PlayerEntity player = (PlayerEntity) source;
        ItemStack stack = player.getHeldItem(player.getActiveHand());
        int lvl = EnchantmentHelper.getEnchantmentLevel(DETONATION, stack);
        if (lvl == 0) return;

        if (player.world.isRemote) return;
        target.world.createExplosion(null, target.getPosX(), target.getPosY(), target.getPosZ(), lvl, Explosion.Mode.NONE);
    }

}