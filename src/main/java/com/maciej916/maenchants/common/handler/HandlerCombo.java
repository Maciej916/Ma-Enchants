package com.maciej916.maenchants.common.handler;

import com.maciej916.maenchants.common.network.ModNetworking;
import com.maciej916.maenchants.common.network.packet.PacketComboReset;
import com.maciej916.maenchants.common.registries.ModEnchantments;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class HandlerCombo {

    @OnlyIn(Dist.CLIENT)
    public static void handlerMiss(PlayerInteractEvent.LeftClickEmpty event) {
        int lvl = event.getItemStack().getEnchantmentLevel(ModEnchantments.COMBO.get());
        if (lvl > 0) {
            ModNetworking.INSTANCE.sendToServer(new PacketComboReset());
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void handlerHitBlock(PlayerInteractEvent.LeftClickBlock event) {
        int lvl = event.getItemStack().getEnchantmentLevel(ModEnchantments.COMBO.get());
        if (lvl > 0) {
            ModNetworking.INSTANCE.sendToServer(new PacketComboReset());
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void handlerTooltip(ItemTooltipEvent event) {
        int lvl = event.getItemStack().getEnchantmentLevel(ModEnchantments.COMBO.get());
        if (lvl > 0) {
            ItemStack stack = event.getItemStack();
            CompoundTag compound = stack.getOrCreateTag();
            int combo = compound.getInt("combo");

            if (combo < 100) {
                event.getToolTip().add(Component.translatable("enchantment.maenchants.combo.lvl", combo));
            } else {
                event.getToolTip().add(Component.translatable("enchantment.maenchants.combo.lvl_max", combo));
            }
        }
    }

}