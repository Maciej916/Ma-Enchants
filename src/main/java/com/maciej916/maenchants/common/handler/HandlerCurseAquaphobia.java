package com.maciej916.maenchants.common.handler;

import com.maciej916.maenchants.common.registries.ModEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.TickEvent;

public class HandlerCurseAquaphobia {

    private static final DamageSource AQUAPHOBIA = new DamageSource("aquaphobia");
    private static int tick = 0;
    private static final EquipmentSlot[] armour = {
            EquipmentSlot.HEAD,
            EquipmentSlot.CHEST,
            EquipmentSlot.LEGS,
            EquipmentSlot.FEET
    };

    public static void handlerPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;
        Level world = player.getCommandSenderWorld();

        BlockPos headPos = new BlockPos(player.getX(), player.getY()+1, player.getZ());
        BlockPos legPos = new BlockPos(player.getX(), player.getY(), player.getZ());

        if (world.getBlockState(headPos).getMaterial().equals(Material.WATER) || world.getBlockState(legPos).getMaterial().equals(Material.WATER)) {
            for (EquipmentSlot slotType : armour) {
                ItemStack stack = player.getItemBySlot(slotType);

                int lvl = stack.getEnchantmentLevel(ModEnchantments.CURSE_AQUAPHOBIA.get());
                if (lvl == 0) continue;

                if (tick % 40 == 0) {
                    player.hurt(AQUAPHOBIA, 2F);
                }
                tick++;
            }
        }
    }

}