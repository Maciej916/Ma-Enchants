package com.maciej916.maenchants.enchantment;

import com.maciej916.maenchants.config.ConfigValues;
import com.maciej916.maenchants.utils.CustomEnchantmentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class EnchantmentWisdom extends Enchantment {

    public EnchantmentWisdom() {
        super(Rarity.RARE, CustomEnchantmentType.SWORD, new EquipmentSlotType[]{
                EquipmentSlotType.MAINHAND
        });
    }

    public int getMinEnchantability(int level) {
        return 5 + 10 * (level - 1);
    }

    public int getMaxLevel() {
        return 5;
    }

    public boolean canApply(ItemStack stack) {
        return ConfigValues.wisdom && super.canApply(stack);
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return ConfigValues.wisdom && super.canApplyAtEnchantingTable(stack);
    }

    public boolean isAllowedOnBooks() {
        return ConfigValues.wisdom;
    }

}