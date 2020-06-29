package com.maciej916.maenchants.common.curse;

import com.maciej916.maenchants.common.config.ConfigValues;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class CurseAquaphobia extends Enchantment {

    public CurseAquaphobia() {
        super(Rarity.RARE, EnchantmentType.ARMOR, new EquipmentSlotType[]{
                EquipmentSlotType.FEET,
        });
    }

    public int getMinEnchantability(int level) {
        return 20;
    }

    public boolean canApply(ItemStack stack) {
        return ConfigValues.curse_aquaphobia && super.canApply(stack);
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return ConfigValues.curse_aquaphobia && super.canApplyAtEnchantingTable(stack);
    }

    public boolean isCurse() {
        return true;
    }

    public boolean isAllowedOnBooks() {
        return ConfigValues.curse_aquaphobia;
    }
}