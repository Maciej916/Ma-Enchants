package com.maciej916.maenchants.common.curse;

import com.maciej916.maenchants.common.config.ConfigValues;
import com.maciej916.maenchants.common.enchantment.BasicEnchantment;
import com.maciej916.maenchants.common.util.CustomEnchantmentCategory;
import net.minecraft.world.entity.EquipmentSlot;

public class CurseButterfingers extends BasicEnchantment {

    public CurseButterfingers() {
        super(Rarity.VERY_RARE, CustomEnchantmentCategory.WEAPON_DIGGER, new EquipmentSlot[]{
                EquipmentSlot.MAINHAND
        });
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
        return 30;
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
        return 60;
    }

    @Override
    public int getMaxLevel() {
        return 1;
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
        return ConfigValues.curse_butterfingers;
    }
}