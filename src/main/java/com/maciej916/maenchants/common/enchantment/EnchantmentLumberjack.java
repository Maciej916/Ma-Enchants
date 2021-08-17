package com.maciej916.maenchants.common.enchantment;

import com.maciej916.maenchants.common.config.ConfigValues;
import com.maciej916.maenchants.common.util.CustomEnchantmentCategory;
import net.minecraft.world.entity.EquipmentSlot;

public class EnchantmentLumberjack extends BasicEnchantment {

    public EnchantmentLumberjack() {
        super(Rarity.UNCOMMON, CustomEnchantmentCategory.AXE, new EquipmentSlot[]{
                EquipmentSlot.MAINHAND
        });
    }

    @Override
    public boolean isEnabled() {
        return ConfigValues.lumberjack;
    }
}
