package com.maciej916.maenchants.common.handler;

import com.maciej916.maenchants.common.network.ModNetworking;
import com.maciej916.maenchants.common.network.packet.PacketCloudParticles;
import com.maciej916.maenchants.common.registries.ModEnchantments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class HandlerSoftFall {

    public static void handlerFall(LivingFallEvent event) {
        Entity source = event.getEntity();
        if (!(source instanceof Player player)) return;
        Level world = player.getCommandSenderWorld();

        ItemStack stack = player.getItemBySlot(EquipmentSlot.FEET);

        int lvl = stack.getEnchantmentLevel(ModEnchantments.SOFT_FALL.get());
        if (lvl == 0) return;

        if (event.getDistance() <= Math.max((lvl * 2), 15) && event.getDistance() > 3) {
            player.getFoodData().addExhaustion(event.getDistance());
            event.setCanceled(true);

            if (world.isClientSide()) {
                ModNetworking.INSTANCE.sendToServer(new PacketCloudParticles());
            }
        }
    }

}