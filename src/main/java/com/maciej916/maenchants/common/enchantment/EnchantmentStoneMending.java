package com.maciej916.maenchants.common.enchantment;

import com.maciej916.maenchants.common.config.ConfigValues;
import com.maciej916.maenchants.common.util.CustomEnchantmentCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class EnchantmentStoneMending extends BasicEnchantment {

    public EnchantmentStoneMending() {
        super(Enchantment.Rarity.RARE, CustomEnchantmentCategory.PICKAXE, new EquipmentSlot[]{
                EquipmentSlot.MAINHAND
        });
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isEnabled() {
        return ConfigValues.stone_mending;
    }
}