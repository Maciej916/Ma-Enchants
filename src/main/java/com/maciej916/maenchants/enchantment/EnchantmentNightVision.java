package com.maciej916.maenchants.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class EnchantmentNightVision extends Enchantment {

    public EnchantmentNightVision() {
        super(Rarity.RARE, EnchantmentType.ARMOR_HEAD, new EquipmentSlotType[]{
                EquipmentSlotType.HEAD,
        });
    }

    @Override
    public int getMinEnchantability(int level) {
        return 15;
    }

}