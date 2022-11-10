package com.maciej916.maenchants.common.handler;

import com.maciej916.maenchants.common.registries.ModEnchantments;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;

import java.text.DecimalFormat;

public class HandlerMomentum {

    public static void handlerBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();

        ItemStack stack = player.getItemInHand(player.getUsedItemHand());

        int lvl = stack.getEnchantmentLevel(ModEnchantments.MOMENTUM.get());
        if (lvl == 0) return;

        CompoundTag compound = stack.getOrCreateTag();
        int momentum = compound.getInt("momentum");
        String cachedBlock = compound.getString("block");
        String currentBlock = event.getState().getBlock().getDescriptionId();
        if (!cachedBlock.equals(currentBlock)) {
            compound.putInt("momentum", 0);
            compound.putString("block", currentBlock);
        } else {
            if (momentum < 100) {
                compound.putInt("momentum", momentum + 1);
            }
        }
    }

    public static void handlerSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        ItemStack stack = player.getItemInHand(player.getUsedItemHand());

        int lvl = stack.getEnchantmentLevel(ModEnchantments.MOMENTUM.get());
        if (lvl == 0) return;

        CompoundTag compound = stack.getOrCreateTag();
        int momentum = compound.getInt("momentum");
        float oldSpeed = event.getOriginalSpeed();
        float newSpeed = oldSpeed + .05f * momentum;
        event.setNewSpeed(newSpeed);
    }

    @OnlyIn(Dist.CLIENT)
    public static void handlerTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();

        int lvl = stack.getEnchantmentLevel(ModEnchantments.MOMENTUM.get());
        if (lvl == 0) return;

        CompoundTag compound = stack.getOrCreateTag();
        int momentum = compound.getInt("momentum");
        String block = compound.getString("block");

        if (block.equals("")) block = "-";

        double speed = 1 + (momentum * .05f);
        DecimalFormat df = new DecimalFormat("#.##");

        if (momentum < 100) {
            event.getToolTip().add(Component.translatable("enchantment.maenchants.momentum.speed", df.format(speed)));
        } else {
            event.getToolTip().add(Component.translatable("enchantment.maenchants.momentum.speed_max", df.format(speed)));
        }

        MutableComponent blockName = Component.translatable(block);
        event.getToolTip().add(Component.translatable("enchantment.maenchants.momentum.block", blockName));
    }
}