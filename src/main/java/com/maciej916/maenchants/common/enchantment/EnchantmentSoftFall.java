package com.maciej916.maenchants.common.enchantment;

import com.maciej916.maenchants.common.config.configs.ServerConfig;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

public class EnchantmentSoftFall extends BasicEnchantment {

    public EnchantmentSoftFall() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[]{
                EquipmentSlot.FEET
        });
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    public boolean checkCompatibility(Enchantment ench) {
        return super.checkCompatibility(ench) && ench != Enchantments.FALL_PROTECTION;
    }

    @Override
    public boolean isEnabled() {
        return ServerConfig.soft_fall.get();
    }
}