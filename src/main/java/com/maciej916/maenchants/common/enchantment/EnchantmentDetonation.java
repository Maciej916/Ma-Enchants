package com.maciej916.maenchants.common.enchantment;

import com.maciej916.maenchants.common.config.ConfigValues;
import com.maciej916.maenchants.common.util.CustomEnchantmentCategory;
import net.minecraft.world.entity.EquipmentSlot;

public class EnchantmentDetonation extends BasicEnchantment {

    public EnchantmentDetonation() {
        super(Rarity.COMMON, CustomEnchantmentCategory.SHOOTABLE, new EquipmentSlot[]{
                EquipmentSlot.MAINHAND
        });
    }

    @Override
    public int getMinCost(int level) {
        return 5 + 10 * (level - 1);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isEnabled() {
        return ConfigValues.detonation;
    }
}