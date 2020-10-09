package com.maciej916.maenchants.common.enchantment;

import com.maciej916.maenchants.common.config.ConfigValues;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class EnchantmentNightVision extends BasicEnchantment {

    public EnchantmentNightVision(String registryName) {
        super(registryName, Rarity.RARE, EnchantmentType.ARMOR_HEAD, new EquipmentSlotType[]{
                EquipmentSlotType.HEAD,
        });
    }

    @Override
    public int getMinEnchantability(int level) {
        return 15;
    }

    @Override
    public boolean isEnabled() {
        return ConfigValues.night_vision;
    }
}