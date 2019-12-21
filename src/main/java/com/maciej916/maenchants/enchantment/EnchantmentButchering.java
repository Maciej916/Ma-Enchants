package com.maciej916.maenchants.enchantment;

import com.maciej916.maenchants.MaEnchants;
import com.maciej916.maenchants.config.ConfigValues;
import com.maciej916.maenchants.utils.CustomEnchantmentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MaEnchants.MODID)
public class EnchantmentButchering extends Enchantment {

    public EnchantmentButchering() {
        super(Rarity.COMMON, CustomEnchantmentType.AXE, new EquipmentSlotType[]{
                EquipmentSlotType.MAINHAND
        });
    }

    public int getMinEnchantability(int level) {
        return level * 8;
    }

    public int getMaxLevel() {
        return 5;
    }

    public boolean canApply(ItemStack stack) {
        if (!ConfigValues.butchering) return false;
        return stack.getItem() instanceof AxeItem || super.canApply(stack);
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return ConfigValues.butchering && super.canApplyAtEnchantingTable(stack);
    }

    public boolean isAllowedOnBooks() {
        return ConfigValues.butchering;
    }

}
