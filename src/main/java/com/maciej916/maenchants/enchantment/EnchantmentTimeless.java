package com.maciej916.maenchants.enchantment;

import com.maciej916.maenchants.config.ConfigValues;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class EnchantmentTimeless extends Enchantment {

    public EnchantmentTimeless() {
        super(Rarity.RARE, EnchantmentType.ALL, new EquipmentSlotType[]{
                EquipmentSlotType.MAINHAND
        });
    }

    public int getMinEnchantability(int level) {
        return 5 + 10 * (level - 1);
    }

    public boolean canApply(ItemStack stack) {
        return ConfigValues.timeless && super.canApply(stack);
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return ConfigValues.timeless && super.canApplyAtEnchantingTable(stack);
    }

    public boolean isAllowedOnBooks() {
        return ConfigValues.timeless;
    }

}