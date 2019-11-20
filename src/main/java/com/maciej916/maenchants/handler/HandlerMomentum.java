package com.maciej916.maenchants.handler;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.text.NBTTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;

import java.text.DecimalFormat;

import static com.maciej916.maenchants.init.ModEnchants.MOMENTUM;

public class HandlerMomentum {

    public static void handlerBreak(BlockEvent.BreakEvent event) {
        PlayerEntity player = event.getPlayer();
        if (EnchantmentHelper.getMaxEnchantmentLevel(MOMENTUM, player) == 0) return;

        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        CompoundNBT compound = stack.getOrCreateTag();
        int momentum = compound.getInt("momentum");
        String cachedBlock = compound.getString("block");
        String currentBlock = event.getState().getBlock().getTranslationKey();
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
        PlayerEntity player = event.getPlayer();
        if (EnchantmentHelper.getMaxEnchantmentLevel(MOMENTUM, player) == 0) return;

        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        CompoundNBT compound = stack.getOrCreateTag();
        int momentum = compound.getInt("momentum");
        float oldSpeed = event.getOriginalSpeed();
        float newSpeed = oldSpeed + .05f * momentum;
        event.setNewSpeed(newSpeed);
    }

    @OnlyIn(Dist.CLIENT)
    public static void handlerTooltip(ItemTooltipEvent event) {
        int level = EnchantmentHelper.getEnchantmentLevel(MOMENTUM, event.getItemStack());
        if (level == 0) return;

        ItemStack stack = event.getItemStack();
        CompoundNBT compound = stack.getOrCreateTag();
        int momentum = compound.getInt("momentum");
        String block = compound.getString("block");

        if (block.equals("")) block = "-";

        double speed = 1 + (momentum * .05f);
        DecimalFormat df = new DecimalFormat("#.##");

        if (momentum < 100) {
            event.getToolTip().add(new TranslationTextComponent("enchantment.ma-enchants.momentum.speed", df.format(speed)));
        } else {
            event.getToolTip().add(new TranslationTextComponent("enchantment.ma-enchants.momentum.speed_max", df.format(speed)));
        }

        TranslationTextComponent blockName = new TranslationTextComponent(block);
        event.getToolTip().add(new TranslationTextComponent("enchantment.ma-enchants.momentum.block", blockName));
    }


}