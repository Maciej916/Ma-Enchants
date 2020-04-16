package com.maciej916.maenchants.curse;

import com.maciej916.maenchants.config.ConfigValues;
import com.maciej916.maenchants.utils.CustomEnchantmentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class CurseButterfingers extends Enchantment {

    public CurseButterfingers() {
        super(Rarity.VERY_RARE, CustomEnchantmentType.WEAPON_DIGGER, new EquipmentSlotType[]{
                EquipmentSlotType.MAINHAND
        });
    }

    public int getMinEnchantability(int enchantmentLevel) {
        return 30;
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return 60;
    }

    public int getMaxLevel() {
        return 1;
    }

    public boolean isTreasureEnchantment() {
        return true;
    }

    public boolean isCurse() {
        return true;
    }

    public boolean canApply(ItemStack stack) {
        return ConfigValues.curse_butterfingers && super.canApply(stack);
    }

}