package com.maciej916.maenchants.common.enchantment;

import com.maciej916.maenchants.common.config.configs.ServerConfig;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class EnchantmentFasterAttack extends BasicEnchantment {

    public EnchantmentFasterAttack() {
        super(Rarity.COMMON, EnchantmentCategory.WEAPON, new EquipmentSlot[]{
                EquipmentSlot.MAINHAND
        });
    }

    @Override
    public int getMinCost(int level) {
        return level * 6;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean isEnabled() {
        return ServerConfig.faster_attack.get();
    }
}