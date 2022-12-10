package com.maciej916.maenchants.common.handler;

import com.maciej916.maenchants.common.registries.ModEnchantments;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class HandlerDetonation {

    public static void handlerHurt(LivingHurtEvent event) {
        Entity source = event.getSource().getEntity();
        DamageSource damageSource = event.getSource();
        LivingEntity target = event.getEntity();

        if (!(source instanceof Player player) || !damageSource.getMsgId().equals("arrow")) return;

        if (!player.level.isClientSide()) {
            ItemStack stack = player.getItemInHand(player.getUsedItemHand());

            int lvl = stack.getEnchantmentLevel(ModEnchantments.DETONATION.get());
            if (lvl == 0) return;

//            target.level.explode(null, target.getX(), target.getY(), target.getZ(), lvl, Explosion.BlockInteraction.NONE);
            target.level.explode(null, target.getX(), target.getY(), target.getZ(), lvl, Level.ExplosionInteraction.NONE);
        }
    }

}