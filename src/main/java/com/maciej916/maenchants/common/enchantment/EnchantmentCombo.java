package com.maciej916.maenchants.common.enchantment;

import com.maciej916.maenchants.common.config.configs.ServerConfig;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class EnchantmentCombo extends BasicEnchantment {

    private static boolean handled = false;

    public EnchantmentCombo() {
        super(Rarity.RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{
                EquipmentSlot.MAINHAND
        });
    }

    @Override
    public int getMinCost(int level) {
        return 15;
    }

    @Override
    public void doPostAttack(LivingEntity entity, Entity target, int p_44688_) {
        if (handled) {
            handled = false;
            return;
        }

        if (!(entity instanceof Player player)) return;

        InteractionHand hand = player.getUsedItemHand();
        if (hand == null) return;

        ItemStack stack = player.getItemInHand(hand);
        CompoundTag compound = stack.getOrCreateTag();
        target.hurt(DamageSource.playerAttack(player), compound.getInt("combo") * .5f);
        int combo = compound.getInt("combo");
        if (combo < 100) {
            compound.putInt("combo", compound.getInt("combo") + 1);
        }
        handled = true;
    }

    @Override
    public boolean isEnabled() {
        return ServerConfig.combo.get();
    }
}