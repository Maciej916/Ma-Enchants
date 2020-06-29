package com.maciej916.maenchants.common.enchantment;

import com.maciej916.maenchants.MaEnchants;
import com.maciej916.maenchants.common.config.ConfigValues;
import com.maciej916.maenchants.common.util.CustomEnchantmentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MaEnchants.MODID)
public class EnchantmentLumberjack extends Enchantment {

    public EnchantmentLumberjack() {
        super(Rarity.UNCOMMON, CustomEnchantmentType.AXE, new EquipmentSlotType[]{
                EquipmentSlotType.MAINHAND
        });
    }

    public boolean canApply(ItemStack stack) {
        return ConfigValues.lumberjack && super.canApply(stack);
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return ConfigValues.lumberjack && super.canApplyAtEnchantingTable(stack);
    }

    public boolean isAllowedOnBooks() {
        return ConfigValues.lumberjack;
    }

}
