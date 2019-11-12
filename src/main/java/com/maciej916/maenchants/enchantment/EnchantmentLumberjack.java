package com.maciej916.maenchants.enchantment;

import com.maciej916.maenchants.MaEnchants;
import com.maciej916.maenchants.utils.CustomEnchantmentType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.maciej916.maenchants.utils.Enchants.LUMBERJACK;

@Mod.EventBusSubscriber(modid = MaEnchants.MODID)
public class EnchantmentLumberjack extends Enchantment {
    public EnchantmentLumberjack(String name) {
        super(Rarity.UNCOMMON, CustomEnchantmentType.AXE, new EquipmentSlotType[]{
                EquipmentSlotType.MAINHAND
        });
        this.setRegistryName(name);
    }

    @SubscribeEvent
    public static void onWoodBreak(BlockEvent.BreakEvent event){
        PlayerEntity player = event.getPlayer();

        if (EnchantmentHelper.getMaxEnchantmentLevel(LUMBERJACK, player) == 0) return;

        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        BlockState state = event.getState();
        Block block = state.getBlock();

        if (!block.isIn(BlockTags.LOGS)) return;

        BlockPos pos = event.getPos();
        while (block.isIn(BlockTags.LOGS)) {
            pos = pos.up();
            state = player.world.getBlockState(pos);
            block = state.getBlock();
            if (block.isIn(BlockTags.LOGS)) {
                stack.damageItem(1, player, playerEntity -> playerEntity.sendBreakAnimation(player.getActiveHand()));
                player.world.destroyBlock(pos,true);
            }
        }
    }
}
