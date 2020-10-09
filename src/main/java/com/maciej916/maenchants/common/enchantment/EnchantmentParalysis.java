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
public class EnchantmentParalysis extends BasicEnchantment {

    public EnchantmentParalysis(String registryName) {
        super(registryName, Rarity.RARE, EnchantmentType.BOW, new EquipmentSlotType[]{
                EquipmentSlotType.MAINHAND,
                EquipmentSlotType.OFFHAND
        });
    }

    @Override
    public int getMinEnchantability(int level) {
        return 5 + 10 * (level - 1);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench) && ench != FLOATING;
    }

    @Override
    public boolean isEnabled() {
        return ConfigValues.paralysis;
    }
}