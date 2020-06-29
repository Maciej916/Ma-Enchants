package com.maciej916.maenchants.common.enchantment;

import com.maciej916.maenchants.common.config.ConfigValues;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class EnchantmentNightVision extends Enchantment {

    public EnchantmentNightVision() {
        super(Rarity.RARE, EnchantmentType.ARMOR_HEAD, new EquipmentSlotType[]{
                EquipmentSlotType.HEAD,
        });
    }

    public int getMinEnchantability(int level) {
        return 15;
    }

    public boolean canApply(ItemStack stack) {
        return ConfigValues.night_vision && super.canApply(stack);
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return ConfigValues.night_vision && super.canApplyAtEnchantingTable(stack);
    }

    public boolean isAllowedOnBooks() {
        return ConfigValues.night_vision;
    }

}