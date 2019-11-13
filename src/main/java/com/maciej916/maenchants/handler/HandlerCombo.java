package com.maciej916.maenchants.handler;

import com.maciej916.maenchants.network.Networking;
import com.maciej916.maenchants.network.PacketCombo;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import static com.maciej916.maenchants.init.ModEnchants.COMBO;

public class HandlerCombo {

    public static void handlerMiss(PlayerInteractEvent.LeftClickEmpty event) {
        if (EnchantmentHelper.getEnchantmentLevel(COMBO, event.getItemStack()) == 0) return;
        Networking.INSTANCE.sendToServer(new PacketCombo());
    }

    public static void handlerHitBlock(PlayerInteractEvent.LeftClickBlock event) {
        if (EnchantmentHelper.getEnchantmentLevel(COMBO, event.getItemStack()) == 0) return;
        Networking.INSTANCE.sendToServer(new PacketCombo());
    }

}