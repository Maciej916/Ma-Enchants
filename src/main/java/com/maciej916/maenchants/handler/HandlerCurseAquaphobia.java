package com.maciej916.maenchants.handler;

import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;

import static com.maciej916.maenchants.init.ModEnchants.CURSE_AQUAPHOBIA;

public class HandlerCurseAquaphobia {

    private static final DamageSource AQUAPHOBIA = new DamageSource("aquaphobia");
    private static int tick = 0;

    public static void handlerPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        PlayerEntity player = event.player;
        World world = player.world;

        ItemStack stack = player.getItemStackFromSlot(EquipmentSlotType.FEET);
        int lvl = EnchantmentHelper.getEnchantmentLevel(CURSE_AQUAPHOBIA, stack);
        if (lvl == 0) return;

        BlockPos headPos = new BlockPos(player.posX, player.posY+1, player.posZ);
        BlockPos legPos = new BlockPos(player.posX, player.posY, player.posZ);

        if (world.getBlockState(headPos).getMaterial().equals(Material.WATER) || world.getBlockState(legPos).getMaterial().equals(Material.WATER)) {
            if (tick % 40 == 0) {
                player.attackEntityFrom(AQUAPHOBIA, 1.5F);
            }
        }
        tick++;
    }

}