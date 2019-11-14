package com.maciej916.maenchants.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class EnchantmentStepAssist extends Enchantment {

    public EnchantmentStepAssist() {
        super(Rarity.RARE, EnchantmentType.ARMOR_FEET, new EquipmentSlotType[]{
                EquipmentSlotType.FEET,
        });
    }

    @Override
    public int getMinEnchantability(int level) {
        return 20;
    }

}