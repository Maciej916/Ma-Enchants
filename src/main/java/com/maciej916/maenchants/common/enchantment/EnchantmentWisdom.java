package com.maciej916.maenchants.common.enchantment;

import com.maciej916.maenchants.common.config.configs.ServerConfig;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class EnchantmentWisdom extends BasicEnchantment {

    public EnchantmentWisdom() {
        super(Rarity.RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{
                EquipmentSlot.MAINHAND
        });
    }

    @Override
    public int getMinCost(int level) {
        return 5 + 10 * (level - 1);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean isEnabled() {
        return ServerConfig.wisdom.get();
    }
}