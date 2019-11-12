package com.maciej916.maenchants.enchantment;

import com.maciej916.maenchants.MaEnchants;
import com.maciej916.maenchants.utils.CustomEnchantmentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import static com.maciej916.maenchants.utils.Enchants.FASTER_ATTACK;

@Mod.EventBusSubscriber(modid = MaEnchants.MODID)
public class EnchantmentFasterAttack extends Enchantment {
    public EnchantmentFasterAttack(String name) {
        super(Rarity.COMMON, CustomEnchantmentType.SWORD, new EquipmentSlotType[]{
                EquipmentSlotType.MAINHAND
        });
        this.setRegistryName(name);
    }

    @Override
    public int getMinEnchantability(int level) {
        return level * 6;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @SubscribeEvent
    public static void onPlayerUpdate(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;
        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        int lvl = EnchantmentHelper.getEnchantmentLevel(FASTER_ATTACK, stack);
        if (lvl == 0) return;

        int swing = ObfuscationReflectionHelper.getPrivateValue(LivingEntity.class, player, "field_184617_aD");
        swing = new Double(swing * (lvl * 0.1 + 1)).intValue();
        swing = Math.min(swing, 100);
        ObfuscationReflectionHelper.setPrivateValue(LivingEntity.class, player, swing, "field_184617_aD");
    }
}