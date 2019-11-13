package com.maciej916.maenchants.enchantment;

import com.maciej916.maenchants.utils.CustomEnchantmentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class EnchantmentFasterAttack extends Enchantment {

    public EnchantmentFasterAttack() {
        super(Rarity.COMMON, CustomEnchantmentType.SWORD, new EquipmentSlotType[]{
                EquipmentSlotType.MAINHAND
        });
    }

    @Override
    public int getMinEnchantability(int level) {
        return level * 6;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}