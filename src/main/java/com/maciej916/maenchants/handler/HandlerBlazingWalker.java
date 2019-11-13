package com.maciej916.maenchants.handler;

import com.google.common.base.Objects;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import static com.maciej916.maenchants.enchantment.EnchantmentBlazingWalker.makeFloor;
import static com.maciej916.maenchants.init.ModEnchants.BLAZING_WALKER;

public class HandlerBlazingWalker {

    public static void handlerUpdate(PlayerEntity player, World world) {
        if (!world.isRemote) {
            int lvl = EnchantmentHelper.getMaxEnchantmentLevel(BLAZING_WALKER, player);
            if (lvl == 0) return;
            BlockPos prevBlockpos = ObfuscationReflectionHelper.getPrivateValue(LivingEntity.class, player, "field_184620_bC");
            BlockPos blockpos = new BlockPos(player);
            if (!Objects.equal(prevBlockpos, blockpos)) {
                makeFloor(player, player.world, blockpos, lvl);
            }
        }
    }

}
