package com.maciej916.maenchants.common.curse;

import com.maciej916.maenchants.common.config.ConfigValues;
import com.maciej916.maenchants.common.enchantment.BasicEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class CurseAquaphobia extends BasicEnchantment {

    public CurseAquaphobia(String registryName) {
        super(registryName, Rarity.RARE, EnchantmentType.ARMOR, new EquipmentSlotType[]{
                EquipmentSlotType.FEET,
        });
    }

    @Override
    public int getMinEnchantability(int level) {
        return 20;
    }

    @Override
    public boolean isCurse() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return ConfigValues.curse_aquaphobia;
    }
}