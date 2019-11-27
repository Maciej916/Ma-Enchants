package com.maciej916.maenchants.enchantment;

import com.maciej916.maenchants.config.ConfigValues;
import com.maciej916.maenchants.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.World;

public class EnchantmentBlazingWalker extends Enchantment {

    public EnchantmentBlazingWalker() {
        super(Enchantment.Rarity.RARE, EnchantmentType.ARMOR_FEET, new EquipmentSlotType[]{
                EquipmentSlotType.FEET
        });
    }

    public int getMinEnchantability(int enchantmentLevel) {
        return enchantmentLevel * 10;
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 15;
    }

    public boolean isTreasureEnchantment() {
        return true;
    }

    public int getMaxLevel() {
        return 2;
    }

    public boolean canApply(ItemStack stack) {
        return ConfigValues.blazing_walker && super.canApply(stack);
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return ConfigValues.blazing_walker && super.canApplyAtEnchantingTable(stack);
    }

    public boolean isAllowedOnBooks() {
        return ConfigValues.blazing_walker;
    }

    public boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench) && ench != Enchantments.FROST_WALKER;
    }
}