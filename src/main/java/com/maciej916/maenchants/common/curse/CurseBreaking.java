package com.maciej916.maenchants.common.curse;

import com.maciej916.maenchants.common.config.configs.ServerConfig;
import com.maciej916.maenchants.common.enchantment.BasicEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class CurseBreaking extends BasicEnchantment {

    public CurseBreaking() {
        super(Rarity.VERY_RARE, EnchantmentCategory.BREAKABLE, EquipmentSlot.values());
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
        return 25;
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
        return 50;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public boolean isCurse() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return ServerConfig.curse_breaking.get();
    }
}