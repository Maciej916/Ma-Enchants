package com.maciej916.maenchants.enchantment;

import com.maciej916.maenchants.MaEnchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Method;

import static com.maciej916.maenchants.MaEnchants.ObjectHolders.QUICK_DRAW;
import static com.maciej916.maenchants.utils.EnchantUtils.getBowInHand;

@Mod.EventBusSubscriber(modid = MaEnchants.MODID)
public class QuickDraw extends Enchantment {
    public QuickDraw() {
        super(Rarity.RARE, EnchantmentType.BOW, new EquipmentSlotType[]{
                EquipmentSlotType.MAINHAND,
                EquipmentSlotType.OFFHAND
        });
        this.setRegistryName("quick_draw");
    }

    @Override
    public int getMinEnchantability(int level) {
        return 5 + 10 * (level - 1);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @SubscribeEvent
    public static void onPlayerUpdate(LivingEvent.LivingUpdateEvent event) {
        if (!(event.getEntity() instanceof PlayerEntity)) return;
        PlayerEntity player = (PlayerEntity) event.getEntity();

        if (EnchantmentHelper.getMaxEnchantmentLevel(QUICK_DRAW, player) == 0) return;
        if (!player.isHandActive()) return;

        ItemStack bow = getBowInHand(player);
        if (bow == null) return;

        int lvl = EnchantmentHelper.getEnchantmentLevel(QUICK_DRAW, bow);
        switch (lvl) {
            case 1:
                if (Math.random() > 0.30) return;
            case 2:
                if (Math.random() > 0.60) return;
            case 3:
                if (Math.random() > 0.80) return;
        }
        tickHeldBow(player);
    }

    private static void tickHeldBow(PlayerEntity player) {
        try {
            Method m = ObfuscationReflectionHelper.findMethod(LivingEntity.class,"func_184608_ct");
            m.invoke(player);
        }
        catch (Exception e) {
            System.out.println("Reflection error " + e.getMessage());
        }
    }
}