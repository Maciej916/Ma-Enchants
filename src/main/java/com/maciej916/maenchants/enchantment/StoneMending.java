package com.maciej916.maenchants.enchantment;

import com.maciej916.maenchants.MaEnchants;
import com.maciej916.maenchants.utils.EnchantmentType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.maciej916.maenchants.MaEnchants.ObjectHolders.STONE_MENDING;

@Mod.EventBusSubscriber(modid = MaEnchants.MODID)
public class StoneMending extends Enchantment {
    public StoneMending() {
        super(Enchantment.Rarity.RARE, EnchantmentType.PICKAXE, new EquipmentSlotType[]{
                EquipmentSlotType.MAINHAND
        });
        this.setRegistryName("stone_mending");
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        PlayerEntity player = event.getPlayer();
        BlockState state = event.getState();

        if (EnchantmentHelper.getMaxEnchantmentLevel(STONE_MENDING, player) == 0) return;
        if (state.getBlock() != Blocks.STONE) return;

        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        int lvl = EnchantmentHelper.getEnchantmentLevel(STONE_MENDING, stack);
        switch (lvl) {
            case 1: if (Math.random()> 0.30) return;
            case 2: if (Math.random()> 0.60) return;
            case 3: if (Math.random()> 0.80) return;
        }

        stack.setDamage(stack.getDamage() - 2);
    }
}
