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

        if (!player.level.isClientSide) {
            BlockPos blockpos = player.blockPosition();
            BlockPos lastPos = ObfuscationReflectionHelper.getPrivateValue(LivingEntity.class, player, "lastPos");

            if (!Objects.equal(lastPos, blockpos)) {
                makeFloor(player, player.level, blockpos, lvl);
            }
        }
    }

    public static void makeFloor(LivingEntity p_45019_, Level p_45020_, BlockPos p_45021_, int p_45022_) {
        if (p_45019_.isOnGround()) {
            BlockState blockstate = ModBlocks.MELTED_COBBLESTONE.defaultBlockState();
            float f = (float)Math.min(16, 2 + p_45022_);
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(BlockPos blockpos : BlockPos.betweenClosed(p_45021_.offset((double)(-f), -1.0D, (double)(-f)), p_45021_.offset((double)f, -1.0D, (double)f))) {
                if (blockpos.closerThan(p_45019_.position(), (double)f)) {
                    blockpos$mutableblockpos.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                    BlockState blockstate1 = p_45020_.getBlockState(blockpos$mutableblockpos);
                    if (blockstate1.isAir()) {
                        BlockState blockstate2 = p_45020_.getBlockState(blockpos);
                        boolean isFull = blockstate2.getBlock() == Blocks.LAVA && blockstate2.getValue(LiquidBlock.LEVEL) == 0; //TODO: Forge, modded waters?
                        if (blockstate2.getMaterial() == Material.LAVA && isFull && blockstate.canSurvive(p_45020_, blockpos) && p_45020_.isUnobstructed(blockstate, blockpos, CollisionContext.empty()) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(p_45019_, net.minecraftforge.common.util.BlockSnapshot.create(p_45020_.dimension(), p_45020_, blockpos), net.minecraft.core.Direction.UP)) {
                            p_45020_.setBlockAndUpdate(blockpos, blockstate);
                            p_45020_.getBlockTicks().scheduleTick(blockpos, ModBlocks.MELTED_COBBLESTONE, Mth.nextInt(p_45019_.getRandom(), 20, 60));
                        }
                    }
                }
            }

        }
    }

}
