package com.maciej916.maenchants.enchantment;

import com.maciej916.maenchants.config.ConfigValues;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import static com.maciej916.maenchants.init.ModEnchants.ICE_ASPECT;

public class EnchantmentIceAspect extends Enchantment {

    public EnchantmentIceAspect() {
        super(Rarity.RARE, EnchantmentType.WEAPON, new EquipmentSlotType[]{
                EquipmentSlotType.MAINHAND
        });
    }

    public int getMinEnchantability(int level) {
        return 5 + 10 * (level - 1);
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return super.getMinEnchantability(enchantmentLevel) + 50;
    }

    public int getMaxLevel() {
        return 2;
    }

    public boolean canApply(ItemStack stack) {
        return ConfigValues.ice_aspect && super.canApply(stack);
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return ConfigValues.ice_aspect && super.canApplyAtEnchantingTable(stack);
    }

    public boolean isAllowedOnBooks() {
        return ConfigValues.ice_aspect;
    }

}