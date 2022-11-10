package com.maciej916.maenchants.common.enchantment;

import com.maciej916.maenchants.common.interfaces.IEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class BasicEnchantment extends Enchantment implements IEnchantment {

    protected BasicEnchantment(Rarity rarityIn, EnchantmentCategory typeIn, EquipmentSlot[] slots) {
        super(rarityIn, typeIn, slots);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return isEnabled() && super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return isEnabled() && super.canEnchant(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {
        return isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
