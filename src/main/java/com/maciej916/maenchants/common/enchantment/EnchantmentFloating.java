package com.maciej916.maenchants.common.enchantment;

import com.maciej916.maenchants.common.config.ConfigValues;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import static com.maciej916.maenchants.common.registries.ModEnchants.PARALYSIS;

public class EnchantmentFloating extends BasicEnchantment {

    public EnchantmentFloating() {
        super(Rarity.RARE, EnchantmentCategory.BOW, new EquipmentSlot[]{
                EquipmentSlot.MAINHAND,
                EquipmentSlot.OFFHAND
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
    public boolean checkCompatibility(Enchantment ench) {
        return super.checkCompatibility(ench) && ench != PARALYSIS;
    }

    @Override
    public boolean isEnabled() {
        return ConfigValues.floating;
    }
}