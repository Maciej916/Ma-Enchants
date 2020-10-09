package com.maciej916.maenchants.common.curse;

import com.maciej916.maenchants.common.config.ConfigValues;
import com.maciej916.maenchants.common.enchantment.BasicEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class CurseDeath extends BasicEnchantment {

    public CurseDeath(String registryName) {
        super(registryName, Rarity.VERY_RARE, EnchantmentType.BREAKABLE, new EquipmentSlotType[]{
                EquipmentSlotType.MAINHAND
        });
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 30;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return 60;
    }

    @Override
    public int getMaxLevel() {
        return 1;
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
        return ConfigValues.curse_death;
    }
}