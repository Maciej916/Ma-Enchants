package com.maciej916.maenchants.common.enchantment;

import com.maciej916.maenchants.common.config.ConfigValues;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class EnchantmentTimeless extends BasicEnchantment {

    public EnchantmentTimeless() {
        super(Rarity.RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{
                EquipmentSlot.MAINHAND
        });
    }

    @Override
    public int getMinCost(int level) {
        return 5 + 10 * (level - 1);
    }

    @Override
    public boolean isEnabled() {
        return ConfigValues.timeless;
    }
}