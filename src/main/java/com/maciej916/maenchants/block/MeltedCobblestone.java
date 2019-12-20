package com.maciej916.maenchants.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class MeltedCobblestone extends Block {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_3;

    public MeltedCobblestone(Block.Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(AGE, Integer.valueOf(0)));
    }

    public void func_225534_a_(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if ((random.nextInt(3) == 0 || this.shouldMelt(worldIn, pos, 4)) && worldIn.getLight(pos) > 11 - (Integer)state.get(AGE) - state.getOpacity(worldIn, pos) && this.slightlyMelt(state, worldIn, pos)) {
            BlockPos.PooledMutable blockpos$pooledmutable = BlockPos.PooledMutable.retain();
            Throwable var6 = null;

            try {
                Direction[] var7 = Direction.values();
                int var8 = var7.length;

                for(int var9 = 0; var9 < var8; ++var9) {
                    Direction direction = var7[var9];
                    blockpos$pooledmutable.setPos(pos).move(direction);
                    BlockState blockstate = worldIn.getBlockState(blockpos$pooledmutable);
                    if (blockstate.getBlock() == this && !this.slightlyMelt(blockstate, worldIn, blockpos$pooledmutable)) {
                        worldIn.getPendingBlockTicks().scheduleTick(blockpos$pooledmutable, this, MathHelper.nextInt(random, 20, 40));
                    }
                }
            } catch (Throwable var19) {
                var6 = var19;
                throw var19;
            } finally {
                if (blockpos$pooledmutable != null) {
                    if (var6 != null) {
                        try {
                            blockpos$pooledmutable.close();
                        } catch (Throwable var18) {
                            var6.addSuppressed(var18);
                        }
                    } else {
                        blockpos$pooledmutable.close();
                    }
                }

            }
        } else {
            worldIn.getPendingBlockTicks().scheduleTick(pos, this, MathHelper.nextInt(random, 20, 40));
        }

    }

    private boolean slightlyMelt(BlockState state, World worldIn, BlockPos pos) {
        int i = state.get(AGE);
        if (i < 3) {
            worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(i + 1)), 2);
            return false;
        } else {
            this.turnIntoLava(state, worldIn, pos);
            return true;
        }
    }

    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (blockIn == this && this.shouldMelt(worldIn, pos, 2)) {
            this.turnIntoLava(state, worldIn, pos);
        }

        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
    }

    private boolean shouldMelt(IBlockReader worldIn, BlockPos pos, int neighborsRequired) {
        int i = 0;
        BlockPos.PooledMutable blockpos$pooledmutable = BlockPos.PooledMutable.retain();
        Throwable var6 = null;

        try {
            Direction[] var7 = Direction.values();
            int var8 = var7.length;

            for(int var9 = 0; var9 < var8; ++var9) {
                Direction direction = var7[var9];
                blockpos$pooledmutable.setPos(pos).move(direction);
                if (worldIn.getBlockState(blockpos$pooledmutable).getBlock() == this) {
                    ++i;
                    if (i >= neighborsRequired) {
                        boolean flag = false;
                        boolean var12 = flag;
                        return var12;
                    }
                }
            }

            boolean var24 = true;
            return var24;
        } catch (Throwable var22) {
            var6 = var22;
            throw var22;
        } finally {
            if (blockpos$pooledmutable != null) {
                if (var6 != null) {
                    try {
                        blockpos$pooledmutable.close();
                    } catch (Throwable var21) {
                        var6.addSuppressed(var21);
                    }
                } else {
                    blockpos$pooledmutable.close();
                }
            }

        }
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }

    protected void turnIntoLava(BlockState p_196454_1_, World p_196454_2_, BlockPos p_196454_3_) {
        p_196454_2_.setBlockState(p_196454_3_, Blocks.LAVA.getDefaultState());
        p_196454_2_.neighborChanged(p_196454_3_, Blocks.LAVA, p_196454_3_);
    }

    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
        Material material = worldIn.getBlockState(pos.down()).getMaterial();
        if (material.blocksMovement() || material.isLiquid()) {
            worldIn.setBlockState(pos, Blocks.LAVA.getDefaultState());
        }
    }
}