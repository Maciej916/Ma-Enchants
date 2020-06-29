package com.maciej916.maenchants.common.curse;

import com.maciej916.maenchants.common.config.ConfigValues;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class CurseBreaking extends Enchantment {

    public CurseBreaking() {
        super(Rarity.VERY_RARE, EnchantmentType.BREAKABLE, EquipmentSlotType.values());
    }

    public int getMinEnchantability(int enchantmentLevel) {
        return 25;
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return 50;
    }

    public int getMaxLevel() {
        return 3;
    }

    public boolean isTreasureEnchantment() {
        return true;
    }

    public boolean isCurse() {
        return true;
    }

    public boolean canApply(ItemStack stack) {
        return ConfigValues.curse_breaking && super.canApply(stack);
    }

}