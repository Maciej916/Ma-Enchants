package com.maciej916.maenchants.enchantment;

import com.maciej916.maenchants.MaEnchants;
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

import static com.maciej916.maenchants.utils.EnchantUtils.getBowInHand;
import static com.maciej916.maenchants.utils.Enchants.FLOATING;

@Mod.EventBusSubscriber(modid = MaEnchants.MODID)
public class EnchantmentFloating extends Enchantment {
    public EnchantmentFloating(String name) {
        super(Rarity.RARE, EnchantmentType.BOW, new EquipmentSlotType[]{
                EquipmentSlotType.MAINHAND,
                EquipmentSlotType.OFFHAND
        });
        this.setRegistryName(name);
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
    public void onEntityDamaged(LivingEntity user, Entity target, int level)  {
        if (!(target instanceof LivingEntity)) return;
        PlayerEntity player = (PlayerEntity) user;

        if (EnchantmentHelper.getMaxEnchantmentLevel(FLOATING, player) == 0) return;

        ItemStack bow = getBowInHand(player);
        if (bow == null) return;

        int lvl = EnchantmentHelper.getEnchantmentLevel(FLOATING, bow);

        LivingEntity livingTarget = (LivingEntity) target;
        livingTarget.addPotionEffect(new EffectInstance(Effects.LEVITATION, lvl * 20, 1));
    }
}