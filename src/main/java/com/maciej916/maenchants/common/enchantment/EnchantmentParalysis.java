package com.maciej916.maenchants.common.enchantment;

import com.maciej916.maenchants.MaEnchants;
import com.maciej916.maenchants.common.config.ConfigValues;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;

import static com.maciej916.maenchants.common.registries.ModEnchants.FLOATING;

@Mod.EventBusSubscriber(modid = MaEnchants.MODID)
public class EnchantmentParalysis extends Enchantment {

    public EnchantmentParalysis() {
        super(Rarity.RARE, EnchantmentType.BOW, new EquipmentSlotType[]{
                EquipmentSlotType.MAINHAND,
                EquipmentSlotType.OFFHAND
        });
    }

    public int getMinEnchantability(int level) {
        return 5 + 10 * (level - 1);
    }

    public int getMaxLevel() {
        return 3;
    }

    public boolean canApply(ItemStack stack) {
        return ConfigValues.paralysis && super.canApply(stack);
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return ConfigValues.paralysis && super.canApplyAtEnchantingTable(stack);
    }

    public boolean isAllowedOnBooks() {
        return ConfigValues.paralysis;
    }

    public boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench) && ench != FLOATING;
    }

}