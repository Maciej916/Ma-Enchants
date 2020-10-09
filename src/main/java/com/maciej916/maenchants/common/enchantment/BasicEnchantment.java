package com.maciej916.maenchants.common.enchantment;

import com.maciej916.maenchants.common.config.ConfigValues;
import com.maciej916.maenchants.interfaces.IEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class BasicEnchantment extends Enchantment implements IEnchantment {

    String registryName;

    protected BasicEnchantment(String registryName, Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
        super(rarityIn, typeIn, slots);
        this.registryName = registryName;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return isEnabled() && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return isEnabled() && super.canApply(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getRegistry() {
        return registryName;
    }
}
