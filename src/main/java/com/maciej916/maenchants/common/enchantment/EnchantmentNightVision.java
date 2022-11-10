package com.maciej916.maenchants.common.enchantment;

import com.maciej916.maenchants.common.config.configs.ServerConfig;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class EnchantmentNightVision extends BasicEnchantment {

    public EnchantmentNightVision() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR_HEAD, new EquipmentSlot[]{
                EquipmentSlot.HEAD,
        });
    }

    @Override
    public int getMinCost(int level) {
        return 15;
    }

    @Override
    public boolean isEnabled() {
        return ServerConfig.night_vision.get();
    }
}