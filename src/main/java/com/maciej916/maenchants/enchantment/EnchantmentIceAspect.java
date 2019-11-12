package com.maciej916.maenchants.enchantment;

import com.maciej916.maenchants.MaEnchants;
import com.maciej916.maenchants.network.Networking;
import com.maciej916.maenchants.network.PacketCombo;
import com.maciej916.maenchants.utils.CustomEnchantmentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.maciej916.maenchants.utils.EnchantUtils.getBowInHand;
import static com.maciej916.maenchants.utils.Enchants.*;
import static com.maciej916.maenchants.utils.Enchants.PARALYSIS;

@Mod.EventBusSubscriber(modid = MaEnchants.MODID)
public class EnchantmentIceAspect extends Enchantment {
    public EnchantmentIceAspect(String name) {
        super(Rarity.UNCOMMON, CustomEnchantmentType.SWORD, new EquipmentSlotType[]{
                EquipmentSlotType.MAINHAND
        });
        this.setRegistryName(name);
    }

    @Override
    public int getMinEnchantability(int level) {
        return 5 + 10 * (level - 1);
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public void onEntityDamaged(LivingEntity user, Entity target, int level)  {
        if (!(target instanceof LivingEntity)) return;
        PlayerEntity player = (PlayerEntity) user;

        if (EnchantmentHelper.getMaxEnchantmentLevel(ICE_ASPECT, player) == 0) return;

        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        int lvl = EnchantmentHelper.getEnchantmentLevel(ICE_ASPECT, stack);

        LivingEntity livingTarget = (LivingEntity) target;
        livingTarget.addPotionEffect(new EffectInstance(Effects.SLOWNESS, lvl * 20, 100));
    }
}