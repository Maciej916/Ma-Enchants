package com.maciej916.maenchants.enchantment;

import com.maciej916.maenchants.MaEnchants;
import com.maciej916.maenchants.utils.CustomEnchantmentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.maciej916.maenchants.utils.Enchants.LIFESTEAL;

@Mod.EventBusSubscriber(modid = MaEnchants.MODID)
public class EnchantmentLifesteal extends Enchantment {
    public EnchantmentLifesteal(String name) {
        super(Rarity.RARE, CustomEnchantmentType.SWORD, new EquipmentSlotType[]{
                EquipmentSlotType.MAINHAND
        });
        this.setRegistryName(name);
    }

    @Override
    public int getMinEnchantability(int level) {
        return 15;
    }

    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (!(event.getSource().getTrueSource() instanceof PlayerEntity)) return;
        PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();

        if (EnchantmentHelper.getMaxEnchantmentLevel(LIFESTEAL, player) == 0) return;

        LivingEntity entity = event.getEntityLiving();
        float damage = event.getAmount();
        entity.attackEntityFrom(DamageSource.GENERIC, damage*1.5f);
        player.heal(damage);
    }
}