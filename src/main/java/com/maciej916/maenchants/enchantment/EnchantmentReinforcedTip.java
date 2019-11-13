package com.maciej916.maenchants.enchantment;

import com.maciej916.maenchants.utils.CustomEnchantmentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class EnchantmentReinforcedTip extends Enchantment {

    public EnchantmentReinforcedTip() {
        super(Rarity.UNCOMMON, CustomEnchantmentType.PICKAXE, new EquipmentSlotType[]{
                EquipmentSlotType.MAINHAND
        });
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}

