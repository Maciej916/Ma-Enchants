package com.maciej916.maenchants.common.handler;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Explosion;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import static com.maciej916.maenchants.common.registries.ModEnchants.DETONATION;

public class HandlerDetonation {

    public static void handlerAttack(LivingHurtEvent event) {
        Entity source = event.getSource().getEntity();
        DamageSource damageSource = event.getSource();
        LivingEntity target = (LivingEntity) event.getEntity();

        if (!(source instanceof Player player) || !damageSource.getMsgId().equals("arrow")) return;
        if (player.level.isClientSide()) return;

        InteractionHand hand = player.getUsedItemHand();
        if (hand == null) return;

        ItemStack stack = player.getItemInHand(hand);
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(DETONATION, stack);
        if (lvl == 0) return;

        target.level.explode(null, target.getX(), target.getY(), target.getZ(), lvl, Explosion.BlockInteraction.NONE);
    }

}