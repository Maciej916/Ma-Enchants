package com.maciej916.maenchants.enchantment;

import com.maciej916.maenchants.MaEnchants;
import com.maciej916.maenchants.config.ConfigValues;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.common.Mod;

import static com.maciej916.maenchants.init.ModEnchants.PARALYSIS;

@Mod.EventBusSubscriber(modid = MaEnchants.MODID)
public class EnchantmentParalysis extends Enchantment {

    public EnchantmentParalysis() {
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
        return ConfigValues.paralysis && super.canApply(stack);
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return ConfigValues.paralysis && super.canApplyAtEnchantingTable(stack);
    }

    public boolean isAllowedOnBooks() {
        return ConfigValues.paralysis;
    }

    public void onEntityDamaged(LivingEntity user, Entity target, int level)  {
        if (!(user instanceof PlayerEntity)) return;
        if (!(target instanceof LivingEntity)) return;

        PlayerEntity player = (PlayerEntity) user;
        LivingEntity livingTarget = (LivingEntity) target;

        int lvl = EnchantmentHelper.getMaxEnchantmentLevel(PARALYSIS, player);
        if (lvl == 0) return;

        livingTarget.addPotionEffect(new EffectInstance(Effects.SLOWNESS, lvl * 20, 100, false, false));
        livingTarget.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, lvl * 20, 100, false, false));
        livingTarget.addPotionEffect(new EffectInstance(Effects.WEAKNESS, lvl * 20, 100, false, false));
    }

}