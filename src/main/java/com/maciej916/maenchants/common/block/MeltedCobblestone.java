package com.maciej916.maenchants.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.Random;

public class MeltedCobblestone extends Block {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;

    public MeltedCobblestone() {
        super(Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(5f, 6.0F).sound(SoundType.STONE).randomTicks());
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, Random random) {
        this.tick(blockState, level, blockPos, random);
    }

    public void tick(BlockState blockState, ServerLevel level, BlockPos blockPos, Random random) {
        if ((random.nextInt(3) == 0 || this.fewerNeigboursThan(level, blockPos, 4)) && level.getMaxLocalRawBrightness(blockPos) > 11 - blockState.getValue(AGE) - blockState.getLightBlock(level, blockPos) && this.slightlyMelt(blockState, level, blockPos)) {
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(Direction direction : Direction.values()) {
                blockpos$mutableblockpos.setWithOffset(blockPos, direction);
                BlockState blockstate = level.getBlockState(blockpos$mutableblockpos);
                if (blockstate.is(this) && !this.slightlyMelt(blockstate, level, blockpos$mutableblockpos)) {
                    level.m_186460_(blockpos$mutableblockpos, this, Mth.nextInt(random, 20, 40));
                }
            }

        } else {
            level.m_186460_(blockPos, this, Mth.nextInt(random, 20, 40));
        }
    }

    private boolean slightlyMelt(BlockState blockState, Level level, BlockPos blockPos) {
        int i = blockState.getValue(AGE);
        if (i < 3) {
            level.setBlock(blockPos, blockState.setValue(AGE, Integer.valueOf(i + 1)), 2);
            return false;
        } else {
            this.melt(blockState, level, blockPos);
            return true;
        }
    }

    public void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, BlockPos blockPos1, boolean p_53584_) {
        if (block.defaultBlockState().is(this) && this.fewerNeigboursThan(level, blockPos, 2)) {
            this.melt(blockState, level, blockPos);
        }

        super.neighborChanged(blockState, level, blockPos, block, blockPos1, p_53584_);
    }

    private boolean fewerNeigboursThan(BlockGetter blockGetter, BlockPos blockPos, int p_53568_) {
        int i = 0;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(Direction direction : Direction.values()) {
            blockpos$mutableblockpos.setWithOffset(blockPos, direction);
            if (blockGetter.getBlockState(blockpos$mutableblockpos).is(this)) {
                ++i;
                if (i >= p_53568_) {
                    return false;
                }
            }
        }

        return true;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_53586_) {
        p_53586_.add(AGE);
    }

    public ItemStack getCloneItemStack(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        return ItemStack.EMPTY;
    }

    protected void melt(BlockState blockState, Level level, BlockPos blockPos) {
        level.setBlockAndUpdate(blockPos, Blocks.LAVA.defaultBlockState());
        level.neighborChanged(blockPos, Blocks.LAVA, blockPos);
    }
}
