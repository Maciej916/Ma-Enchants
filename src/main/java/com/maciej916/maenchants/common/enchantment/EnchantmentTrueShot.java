package com.maciej916.maenchants.common.enchantment;

import com.maciej916.maenchants.common.config.ConfigValues;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class EnchantmentTrueShot extends BasicEnchantment {

    public EnchantmentTrueShot() {
        super(Rarity.VERY_RARE, EnchantmentCategory.BOW, new EquipmentSlot[]{
                EquipmentSlot.MAINHAND,
                EquipmentSlot.OFFHAND
        });
    }

    @Override
    public int getMinCost(int level) {
        return 20;
    }

    @Override
    public boolean isEnabled() {
        return ConfigValues.true_shot;
    }
}
