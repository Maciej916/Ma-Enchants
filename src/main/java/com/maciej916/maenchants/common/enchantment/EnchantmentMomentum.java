package com.maciej916.maenchants.common.enchantment;

import com.maciej916.maenchants.common.config.ConfigValues;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class EnchantmentMomentum extends BasicEnchantment {

    public EnchantmentMomentum() {
        super(Rarity.RARE, EnchantmentCategory.DIGGER, new EquipmentSlot[]{
                EquipmentSlot.MAINHAND
        });
    }

    @Override
    public int getMinCost(int level) {
        return 15;
    }

    @Override
    public boolean isEnabled() {
        return ConfigValues.momentum;
    }
}