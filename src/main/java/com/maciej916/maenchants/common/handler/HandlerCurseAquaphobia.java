package com.maciej916.maenchants.common.handler;

import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;

import static com.maciej916.maenchants.common.registries.ModEnchants.CURSE_AQUAPHOBIA;

public class HandlerCurseAquaphobia {

    private static final DamageSource AQUAPHOBIA = new DamageSource("aquaphobia");
    private static int tick = 0;
    private static EquipmentSlotType armour[] = {
            EquipmentSlotType.HEAD,
            EquipmentSlotType.CHEST,
            EquipmentSlotType.LEGS,
            EquipmentSlotType.FEET
    };

    public static void handlerPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        PlayerEntity player = event.player;
        World world = player.world;

        BlockPos headPos = new BlockPos(player.getPosX(), player.getPosY()+1, player.getPosZ());
        BlockPos legPos = new BlockPos(player.getPosX(), player.getPosY(), player.getPosZ());

        if (world.getBlockState(headPos).getMaterial().equals(Material.WATER) || world.getBlockState(legPos).getMaterial().equals(Material.WATER)) {
            for (EquipmentSlotType slotType : armour) {
                ItemStack stack = player.getItemStackFromSlot(slotType);
                int lvl = EnchantmentHelper.getEnchantmentLevel(CURSE_AQUAPHOBIA, stack);
                if (lvl == 0) continue;
                if (tick % 40 == 0) {
                    player.attackEntityFrom(AQUAPHOBIA, 1.5F);
                }
                tick++;
            }
        }
    }

}