package com.maciej916.maenchants.enchantment;

import com.maciej916.maenchants.MaEnchants;
import com.maciej916.maenchants.utils.CustomEnchantmentType;
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

import static com.maciej916.maenchants.utils.Enchants.REINFORCED_TIP;

@Mod.EventBusSubscriber(modid = MaEnchants.MODID)
public class EnchantmentReinforcedTip extends Enchantment {
    public EnchantmentReinforcedTip(String name) {
        super(Rarity.UNCOMMON, CustomEnchantmentType.PICKAXE, new EquipmentSlotType[]{
                EquipmentSlotType.MAINHAND
        });
        this.setRegistryName(name);
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

