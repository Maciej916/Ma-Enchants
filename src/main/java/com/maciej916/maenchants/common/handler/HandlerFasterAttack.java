package com.maciej916.maenchants.common.handler;

import com.maciej916.maenchants.common.capabilities.player.IPlayerCapability;
import com.maciej916.maenchants.common.registries.ModEnchantments;
import com.maciej916.maenchants.common.registries.ModMobEffects;
import com.maciej916.maenchants.common.util.PlayerUtil;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;

public class HandlerFasterAttack {

    public static void handlerPlayerTick(Player player) {
        IPlayerCapability enchantsCap = PlayerUtil.getAliveEnchantsCapability(player);
        if (enchantsCap == null) return;

        ItemStack stack = player.getItemInHand(player.getUsedItemHand());
        int lvl = stack.getEnchantmentLevel(ModEnchantments.FASTER_ATTACK.get());
        if (lvl > 0) {
            if (enchantsCap.getFasterAttack() == 0) {

//                MobEffectInstance instance = new MobEffectInstance(ModMobEffects.FASTER_ATTACK.get(), 900, lvl, false, false, false);
//                instance.setNoCounter(true);
//                player.addEffect(instance);

                double baseAttack = Attributes.ATTACK_SPEED.getDefaultValue();
                Objects.requireNonNull(player.getAttribute(Attributes.ATTACK_SPEED)).setBaseValue(baseAttack * lvl);
                enchantsCap.setFasterAttack(lvl);
            }
        } else {
            if (enchantsCap.getFasterAttack() != 0) {
                double attackSpeed = Objects.requireNonNull(player.getAttribute(Attributes.ATTACK_SPEED)).getValue();
                Objects.requireNonNull(player.getAttribute(Attributes.ATTACK_SPEED)).setBaseValue(attackSpeed / enchantsCap.getFasterAttack());
                player.removeEffect(ModMobEffects.FASTER_ATTACK.get());
                enchantsCap.setFasterAttack(0);
            }
        }
    }
}

