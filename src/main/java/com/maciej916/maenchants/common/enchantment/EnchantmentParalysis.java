package com.maciej916.maenchants.common.enchantment;

import com.maciej916.maenchants.MaEnchants;
import com.maciej916.maenchants.common.config.configs.ServerConfig;
import com.maciej916.maenchants.common.registries.ModEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MaEnchants.MODID)
public class EnchantmentParalysis extends BasicEnchantment {

    public EnchantmentParalysis() {
        super(Rarity.RARE, EnchantmentCategory.BOW, new EquipmentSlot[]{
                EquipmentSlot.MAINHAND,
                EquipmentSlot.OFFHAND
        });
    }

    @Override
    public int getMinCost(int level) {
        return 5 + 10 * (level - 1);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean checkCompatibility(Enchantment ench) {
        return super.checkCompatibility(ench) && ench != ModEnchantments.FLOATING.get();
    }

    @Override
    public boolean isEnabled() {
        return ServerConfig.paralysis.get();
    }
}