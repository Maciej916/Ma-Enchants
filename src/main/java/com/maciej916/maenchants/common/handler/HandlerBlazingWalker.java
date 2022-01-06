package com.maciej916.maenchants.common.handler;

import com.google.common.base.Objects;
import com.maciej916.maenchants.common.registries.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import static com.maciej916.maenchants.common.registries.ModEnchants.BLAZING_WALKER;

public class HandlerBlazingWalker {

    public static void handlerPlayerTick(Player player) {
        ItemStack stack = player.getItemBySlot(EquipmentSlot.FEET);
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(BLAZING_WALKER, stack);
        if (lvl == 0) return;

        BlockPos blockpos = player.blockPosition();
        BlockPos lastPos = ObfuscationReflectionHelper.getPrivateValue(LivingEntity.class, player, "lastPos");

        if (!Objects.equal(lastPos, blockpos)) {
            onEntityMoved(player, player.level, blockpos, lvl);
        }
    }

    public static void onEntityMoved(LivingEntity entity, Level level, BlockPos pos, int lvl) {
        if (entity.isOnGround()) {
            BlockState blockstate = ModBlocks.MELTED_COBBLESTONE.getBlock().defaultBlockState();
            float f = (float)Math.min(16, 2 + lvl);
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-f, -1.0D, -f), pos.offset(f, -1.0D, f))) {
                if (blockpos.closerThan(entity.position(), f)) {
                    blockpos$mutableblockpos.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                    BlockState blockstate1 = level.getBlockState(blockpos$mutableblockpos);
                    if (blockstate1.isAir()) {
                        BlockState blockstate2 = level.getBlockState(blockpos);
                        boolean isFull = blockstate2.getBlock() == Blocks.LAVA && blockstate2.getValue(LiquidBlock.LEVEL) == 0; //TODO: Forge, modded waters?
                        if (blockstate2.getMaterial() == Material.LAVA && isFull && blockstate.canSurvive(level, blockpos) && level.isUnobstructed(blockstate, blockpos, CollisionContext.empty()) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(entity, net.minecraftforge.common.util.BlockSnapshot.create(level.dimension(), level, blockpos), net.minecraft.core.Direction.UP)) {
                            level.setBlockAndUpdate(blockpos, blockstate);
                            level.m_186460_(blockpos, ModBlocks.MELTED_COBBLESTONE.getBlock(), Mth.nextInt(entity.getRandom(), 20, 60));
                        }
                    }
                }
            }
        }
    }

}
