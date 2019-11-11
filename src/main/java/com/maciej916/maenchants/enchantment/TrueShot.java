package com.maciej916.maenchants.enchantment;

import com.maciej916.maenchants.MaEnchants;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.maciej916.maenchants.MaEnchants.ObjectHolders.STONE_MENDING;
import static com.maciej916.maenchants.MaEnchants.ObjectHolders.TRUE_SHOT;
import static com.maciej916.maenchants.events.EventTrueShot.trueShotArrows;

@Mod.EventBusSubscriber(modid = MaEnchants.MODID)
public class TrueShot extends Enchantment {
    public TrueShot() {
        super(Rarity.RARE, EnchantmentType.BOW, new EquipmentSlotType[]{
                EquipmentSlotType.MAINHAND,
                EquipmentSlotType.OFFHAND
        });
        this.setRegistryName("true_shot");
    }

    @Override
    public int getMinEnchantability(int level) {
        return 20;
    }

    @SubscribeEvent
    public static void arrowSpawn(EntityJoinWorldEvent event)  {
        Entity entity = event.getEntity();
        if (!(entity instanceof AbstractArrowEntity)) return;
        Entity shooter = ((AbstractArrowEntity) entity).getShooter();
        if (!(shooter instanceof PlayerEntity)) return;
        PlayerEntity player = (PlayerEntity) shooter;
        if (EnchantmentHelper.getMaxEnchantmentLevel(TRUE_SHOT, player) == 0) return;
        entity.setNoGravity(true);
        trueShotArrows.add((AbstractArrowEntity)entity);
    }
}
