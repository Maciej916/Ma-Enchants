package com.maciej916.maenchants.common.curse;

import com.maciej916.maenchants.common.config.ConfigValues;
import com.maciej916.maenchants.common.enchantment.BasicEnchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class CurseBreaking extends BasicEnchantment {

    public CurseBreaking(String registryName) {
        super(registryName, Rarity.VERY_RARE, EnchantmentType.BREAKABLE, EquipmentSlotType.values());
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 25;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return 50;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isTreasureEnchantment() {
        return true;
    }

    @Override
    public boolean isCurse() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return ConfigValues.curse_breaking;
    }
}