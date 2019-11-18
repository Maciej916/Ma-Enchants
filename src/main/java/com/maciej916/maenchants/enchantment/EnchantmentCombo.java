package com.maciej916.maenchants.enchantment;

import com.maciej916.maenchants.config.ConfigValues;
import com.maciej916.maenchants.utils.CustomEnchantmentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;

public class EnchantmentCombo extends Enchantment {

    public EnchantmentCombo() {
        super(Rarity.RARE, CustomEnchantmentType.SWORD, new EquipmentSlotType[]{
                EquipmentSlotType.MAINHAND
        });
    }

    public int getMinEnchantability(int level) {
        return 15;
    }

    public boolean canApply(ItemStack stack) {
        return ConfigValues.combo && super.canApply(stack);
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return ConfigValues.combo && super.canApplyAtEnchantingTable(stack);
    }

    public boolean isAllowedOnBooks() {
        return ConfigValues.combo;
    }

    private static boolean handled = false;

    public void onEntityDamaged(LivingEntity entity, Entity target, int level) {
        if (handled) {
            handled = false;
            return;
        }

        if (!(entity instanceof PlayerEntity)) return;
        PlayerEntity player = (PlayerEntity) entity;
        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        CompoundNBT compound = stack.getOrCreateTag();
        target.attackEntityFrom(DamageSource.causePlayerDamage(player), compound.getInt("combo") * .5f);
        compound.putInt("combo", compound.getInt("combo") + 1);

        handled = true;
    }
}