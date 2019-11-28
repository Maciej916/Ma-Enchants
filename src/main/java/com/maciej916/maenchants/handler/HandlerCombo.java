package com.maciej916.maenchants.handler;

import com.maciej916.maenchants.network.Networking;
import com.maciej916.maenchants.network.PacketComboReset;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import static com.maciej916.maenchants.init.ModEnchants.COMBO;

public class HandlerCombo {

    @OnlyIn(Dist.CLIENT)
    public static void handlerMiss(PlayerInteractEvent.LeftClickEmpty event) {
        if (EnchantmentHelper.getEnchantmentLevel(COMBO, event.getItemStack()) == 0) return;
        Networking.INSTANCE.sendToServer(new PacketComboReset());
    }

    @OnlyIn(Dist.CLIENT)
    public static void handlerHitBlock(PlayerInteractEvent.LeftClickBlock event) {
        if (EnchantmentHelper.getEnchantmentLevel(COMBO, event.getItemStack()) == 0) return;
        Networking.INSTANCE.sendToServer(new PacketComboReset());
    }

    @OnlyIn(Dist.CLIENT)
    public static void handlerTooltip(ItemTooltipEvent event) {
        int level = EnchantmentHelper.getEnchantmentLevel(COMBO, event.getItemStack());
        if (level == 0) return;

        ItemStack stack = event.getItemStack();
        CompoundNBT compound = stack.getOrCreateTag();
        int combo = compound.getInt("combo");

        if (combo < 100) {
            event.getToolTip().add(new TranslationTextComponent("enchantment.ma-enchants.combo.lvl", combo));
        } else {
            event.getToolTip().add(new TranslationTextComponent("enchantment.ma-enchants.combo.lvl_max", combo));
        }
    }

}