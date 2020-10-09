package com.maciej916.maenchants.common.enchantment;

import com.maciej916.maenchants.common.config.ConfigValues;
import com.maciej916.maenchants.common.util.CustomEnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class EnchantmentLumberjack extends BasicEnchantment {

    public EnchantmentLumberjack(String registryName) {
        super(registryName, Rarity.UNCOMMON, CustomEnchantmentType.AXE, new EquipmentSlotType[]{
                EquipmentSlotType.MAINHAND
        });
    }

    @Override
    public boolean isEnabled() {
        return ConfigValues.lumberjack;
    }
}
