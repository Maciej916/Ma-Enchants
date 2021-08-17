package com.maciej916.maenchants.common.enchantment;

import com.maciej916.maenchants.common.config.ConfigValues;
import com.maciej916.maenchants.common.util.CustomEnchantmentCategory;
import net.minecraft.world.entity.EquipmentSlot;

public class EnchantmentButchering extends BasicEnchantment {

    public EnchantmentButchering() {
        super(Rarity.COMMON, CustomEnchantmentCategory.AXE, new EquipmentSlot[]{
                EquipmentSlot.MAINHAND
        });
    }

    @Override
    public int getMinCost(int level) {
        return level * 8;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean isEnabled() {
        return ConfigValues.butchering;
    }
}