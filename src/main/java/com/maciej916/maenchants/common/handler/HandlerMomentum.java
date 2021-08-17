package com.maciej916.maenchants.common.handler;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;

import java.text.DecimalFormat;

import static com.maciej916.maenchants.common.registries.ModEnchants.MOMENTUM;

public class HandlerMomentum {

    public static void handlerBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();

        InteractionHand hand = player.getUsedItemHand();
        if (hand == null) return;

        ItemStack stack = player.getItemInHand(hand);
        if (EnchantmentHelper.getItemEnchantmentLevel(MOMENTUM, stack) == 0) return;

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
        Player player = event.getPlayer();
        if (EnchantmentHelper.getEnchantmentLevel(MOMENTUM, player) == 0) return;

        InteractionHand hand = player.getUsedItemHand();
        if (hand == null) return;

        ItemStack stack = player.getItemInHand(hand);
        CompoundTag compound = stack.getOrCreateTag();
        int momentum = compound.getInt("momentum");
        float oldSpeed = event.getOriginalSpeed();
        float newSpeed = oldSpeed + .05f * momentum;
        event.setNewSpeed(newSpeed);
    }

    @OnlyIn(Dist.CLIENT)
    public static void handlerTooltip(ItemTooltipEvent event) {
        int level = EnchantmentHelper.getItemEnchantmentLevel(MOMENTUM, event.getItemStack());
        if (level == 0) return;

        ItemStack stack = event.getItemStack();
        CompoundTag compound = stack.getOrCreateTag();
        int momentum = compound.getInt("momentum");
        String block = compound.getString("block");

        if (block.equals("")) block = "-";

        double speed = 1 + (momentum * .05f);
        DecimalFormat df = new DecimalFormat("#.##");

        if (momentum < 100) {
            event.getToolTip().add(new TranslatableComponent("enchantment.maenchants.momentum.speed", df.format(speed)));
        } else {
            event.getToolTip().add(new TranslatableComponent("enchantment.maenchants.momentum.speed_max", df.format(speed)));
        }

        TranslatableComponent blockName = new TranslatableComponent(block);
        event.getToolTip().add(new TranslatableComponent("enchantment.maenchants.momentum.block", blockName));
    }


}