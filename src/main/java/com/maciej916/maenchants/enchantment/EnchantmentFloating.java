package com.maciej916.maenchants.enchantment;

import com.maciej916.maenchants.config.ConfigValues;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import static com.maciej916.maenchants.init.ModEnchants.FLOATING;
import static com.maciej916.maenchants.init.ModEnchants.PARALYSIS;

public class EnchantmentFloating extends Enchantment {

    public EnchantmentFloating() {
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
        return ConfigValues.floating && super.canApply(stack);
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return ConfigValues.floating && super.canApplyAtEnchantingTable(stack);
    }

    public boolean isAllowedOnBooks() {
        return ConfigValues.floating;
    }

    public void onEntityDamaged(LivingEntity user, Entity target, int level)  {
        if (!(user instanceof PlayerEntity)) return;
        if (!(target instanceof LivingEntity)) return;

        PlayerEntity player = (PlayerEntity) user;
        LivingEntity livingTarget = (LivingEntity) target;

        int lvl = EnchantmentHelper.getMaxEnchantmentLevel(FLOATING, player);
        if (lvl == 0) return;

        livingTarget.addPotionEffect(new EffectInstance(Effects.LEVITATION, lvl * 20, 1, false, false));
    }

    public boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench) && ench != PARALYSIS;
    }

}