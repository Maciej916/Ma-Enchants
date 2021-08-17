package com.maciej916.maenchants.common.handler;

import com.maciej916.maenchants.common.network.Networking;
import com.maciej916.maenchants.common.network.packet.PacketComboReset;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import static com.maciej916.maenchants.common.registries.ModEnchants.COMBO;

public class HandlerCombo {

    @OnlyIn(Dist.CLIENT)
    public static void handlerMiss(PlayerInteractEvent.LeftClickEmpty event) {
        if (EnchantmentHelper.getItemEnchantmentLevel(COMBO, event.getItemStack()) == 0) return;
        Networking.INSTANCE.sendToServer(new PacketComboReset());
    }

    @OnlyIn(Dist.CLIENT)
    public static void handlerHitBlock(PlayerInteractEvent.LeftClickBlock event) {
        if (EnchantmentHelper.getItemEnchantmentLevel(COMBO, event.getItemStack()) == 0) return;
        Networking.INSTANCE.sendToServer(new PacketComboReset());
    }

    @OnlyIn(Dist.CLIENT)
    public static void handlerTooltip(ItemTooltipEvent event) {
        int level = EnchantmentHelper.getItemEnchantmentLevel(COMBO, event.getItemStack());
        if (level == 0) return;

        ItemStack stack = event.getItemStack();
        CompoundTag compound = stack.getOrCreateTag();
        int combo = compound.getInt("combo");

        if (combo < 100) {
            event.getToolTip().add(new TranslatableComponent("enchantment.maenchants.combo.lvl", combo));
        } else {
            event.getToolTip().add(new TranslatableComponent("enchantment.maenchants.combo.lvl_max", combo));
        }
    }

}