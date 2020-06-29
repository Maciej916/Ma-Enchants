package com.maciej916.maenchants.common.enchantment;

import com.maciej916.maenchants.common.config.ConfigValues;
import com.maciej916.maenchants.common.util.CustomEnchantmentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class EnchantmentReinforcedTip extends Enchantment {

    public EnchantmentReinforcedTip() {
        super(Rarity.UNCOMMON, CustomEnchantmentType.PICKAXE, new EquipmentSlotType[]{
                EquipmentSlotType.MAINHAND
        });
    }

    public boolean canApply(ItemStack stack) {
        return ConfigValues.reinforced_tip && super.canApply(stack);
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return ConfigValues.reinforced_tip && super.canApplyAtEnchantingTable(stack);
    }

    public boolean isAllowedOnBooks() {
        return ConfigValues.reinforced_tip;
    }

    public int getMaxLevel() {
        return 3;
    }

}