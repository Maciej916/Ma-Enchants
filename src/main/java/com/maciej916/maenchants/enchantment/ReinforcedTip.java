package com.maciej916.maenchants.enchantment;

import com.maciej916.maenchants.MaEnchants;
import com.maciej916.maenchants.utils.EnchantmentType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.maciej916.maenchants.MaEnchants.ObjectHolders.REINFORCED_TIP;

@Mod.EventBusSubscriber(modid = MaEnchants.MODID)
public class ReinforcedTip extends Enchantment {
    public ReinforcedTip() {
        super(Rarity.RARE, EnchantmentType.PICKAXE, new EquipmentSlotType[]{
                EquipmentSlotType.MAINHAND
        });
        this.setRegistryName("reinforced_tip");
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        PlayerEntity player = event.getPlayer();
        BlockState state = event.getState();

        if (EnchantmentHelper.getMaxEnchantmentLevel(REINFORCED_TIP, player) == 0) return;
        if (state.getBlock() != Blocks.OBSIDIAN) return;

        int lvl = EnchantmentHelper.getEnchantmentLevel(REINFORCED_TIP, player.getHeldItem(Hand.MAIN_HAND));
        float oldSpeed = event.getOriginalSpeed();
        event.setNewSpeed(oldSpeed * lvl + 1);
    }
}

