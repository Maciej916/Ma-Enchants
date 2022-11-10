package com.maciej916.maenchants.common.enchantment;

import com.maciej916.maenchants.common.config.configs.ServerConfig;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

public class EnchantmentBlazingWalker extends BasicEnchantment {

    public EnchantmentBlazingWalker() {
        super(Enchantment.Rarity.RARE, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[]{
                EquipmentSlot.FEET
        });
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
        return enchantmentLevel * 10;
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
        return this.getMinCost(enchantmentLevel) + 15;
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public boolean checkCompatibility(Enchantment ench) {
        return super.checkCompatibility(ench) && ench != Enchantments.FROST_WALKER;
    }

    @Override
    public boolean isEnabled() {
        return ServerConfig.blazing_walker.get();
    }


}