package com.maciej916.maenchants.handler;

import com.maciej916.maenchants.capabilities.IEnchants;
import com.maciej916.maenchants.utils.PlayerUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import static com.maciej916.maenchants.init.ModEnchants.NIGHT_VISION;

public class HandlerNightVision {

    public static void handlerPlayerTick(PlayerEntity player) {
        IEnchants enchantsCap = PlayerUtil.getAliveEnchantsCapability(player);
        if (enchantsCap == null) return;

        ItemStack stack = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
        int lvl = EnchantmentHelper.getEnchantmentLevel(NIGHT_VISION, stack);

        if (lvl == 0) {
            if (enchantsCap.getNightVision()) {
                player.removePotionEffect(Effects.NIGHT_VISION);
                enchantsCap.setNightVision(false);
            }
        } else {
            if (!enchantsCap.getNightVision()) {
                EffectInstance effectinstance = new EffectInstance(Effects.NIGHT_VISION, 1000000, 100, false, false);
                effectinstance.setPotionDurationMax(true);
                player.addPotionEffect(effectinstance);
                enchantsCap.setNightVision(true);
            }
        }
    }

}

