package com.maciej916.maenchants.common.enchantment;

import com.maciej916.maenchants.common.config.ConfigValues;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class EnchantmentStepAssist extends BasicEnchantment {

    public EnchantmentStepAssist() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[]{
                EquipmentSlot.FEET,
        });
    }

    @Override
    public int getMinCost(int level) {
        return 20;
    }

    @Override
    public boolean isEnabled() {
        return ConfigValues.step_assist;
    }
}