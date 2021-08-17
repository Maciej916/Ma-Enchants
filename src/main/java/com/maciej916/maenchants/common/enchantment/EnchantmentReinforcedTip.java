package com.maciej916.maenchants.common.enchantment;

import com.maciej916.maenchants.common.config.ConfigValues;
import com.maciej916.maenchants.common.util.CustomEnchantmentCategory;
import net.minecraft.world.entity.EquipmentSlot;

public class EnchantmentReinforcedTip extends BasicEnchantment {

    public EnchantmentReinforcedTip() {
        super(Rarity.UNCOMMON, CustomEnchantmentCategory.PICKAXE, new EquipmentSlot[]{
                EquipmentSlot.MAINHAND
        });
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isEnabled() {
        return ConfigValues.reinforced_tip;
    }
}