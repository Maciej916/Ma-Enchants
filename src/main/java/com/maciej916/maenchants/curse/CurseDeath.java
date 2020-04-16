package com.maciej916.maenchants.curse;

import com.maciej916.maenchants.config.ConfigValues;
import com.maciej916.maenchants.utils.CustomEnchantmentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class CurseDeath extends Enchantment {

    public CurseDeath() {
        super(Rarity.VERY_RARE, EnchantmentType.ALL, new EquipmentSlotType[]{
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
        return ConfigValues.curse_death && super.canApply(stack);
    }

}