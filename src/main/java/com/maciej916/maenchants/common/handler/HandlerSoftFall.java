package com.maciej916.maenchants.common.handler;

import com.maciej916.maenchants.common.network.Networking;
import com.maciej916.maenchants.common.network.packet.PacketCloudParticles;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingFallEvent;

import static com.maciej916.maenchants.common.registries.ModEnchants.SOFT_FALL;

public class HandlerSoftFall {

    public static void handlerFall(LivingFallEvent event) {
        Entity source = event.getEntityLiving();
        if (!(source instanceof PlayerEntity)) return;
        PlayerEntity player = (PlayerEntity) source;
        World world = player.world;

        ItemStack stack = player.getItemStackFromSlot(EquipmentSlotType.FEET);
        int lvl = EnchantmentHelper.getEnchantmentLevel(SOFT_FALL, stack);
        if (lvl == 0) return;

        if (event.getDistance() <= Math.max((lvl * 2), 15) && event.getDistance() > 3) {
            player.getFoodStats().addExhaustion(event.getDistance());
            event.setCanceled(true);

            if (world.isRemote()) {
                Networking.INSTANCE.sendToServer(new PacketCloudParticles());
            }
        }
    }

}