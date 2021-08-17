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
        super(Properties.of(Material.LAVA, MaterialColor.COLOR_RED).harvestLevel(5).explosionResistance(6.0F).sound(SoundType.STONE));
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)));
    }

    public void randomTick(BlockState p_53588_, ServerLevel p_53589_, BlockPos p_53590_, Random p_53591_) {
        this.tick(p_53588_, p_53589_, p_53590_, p_53591_);
    }

    public void tick(BlockState p_53574_, ServerLevel p_53575_, BlockPos p_53576_, Random p_53577_) {
        if ((p_53577_.nextInt(3) == 0 || this.fewerNeigboursThan(p_53575_, p_53576_, 4)) && p_53575_.getMaxLocalRawBrightness(p_53576_) > 11 - p_53574_.getValue(AGE) - p_53574_.getLightBlock(p_53575_, p_53576_) && this.slightlyMelt(p_53574_, p_53575_, p_53576_)) {
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(Direction direction : Direction.values()) {
                blockpos$mutableblockpos.setWithOffset(p_53576_, direction);
                BlockState blockstate = p_53575_.getBlockState(blockpos$mutableblockpos);
                if (blockstate.is(this) && !this.slightlyMelt(blockstate, p_53575_, blockpos$mutableblockpos)) {
                    p_53575_.getBlockTicks().scheduleTick(blockpos$mutableblockpos, this, Mth.nextInt(p_53577_, 20, 40));
                }
            }

        } else {
            p_53575_.getBlockTicks().scheduleTick(p_53576_, this, Mth.nextInt(p_53577_, 20, 40));
        }
    }

    private boolean slightlyMelt(BlockState p_53593_, Level p_53594_, BlockPos p_53595_) {
        int i = p_53593_.getValue(AGE);
        if (i < 3) {
            p_53594_.setBlock(p_53595_, p_53593_.setValue(AGE, Integer.valueOf(i + 1)), 2);
            return false;
        } else {
            this.melt(p_53593_, p_53594_, p_53595_);
            return true;
        }
    }

    public void neighborChanged(BlockState p_53579_, Level p_53580_, BlockPos p_53581_, Block p_53582_, BlockPos p_53583_, boolean p_53584_) {
        if (p_53582_.defaultBlockState().is(this) && this.fewerNeigboursThan(p_53580_, p_53581_, 2)) {
            this.melt(p_53579_, p_53580_, p_53581_);
        }

        super.neighborChanged(p_53579_, p_53580_, p_53581_, p_53582_, p_53583_, p_53584_);
    }

    private boolean fewerNeigboursThan(BlockGetter p_53566_, BlockPos p_53567_, int p_53568_) {
        int i = 0;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(Direction direction : Direction.values()) {
            blockpos$mutableblockpos.setWithOffset(p_53567_, direction);
            if (p_53566_.getBlockState(blockpos$mutableblockpos).is(this)) {
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

    public ItemStack getCloneItemStack(BlockGetter p_53570_, BlockPos p_53571_, BlockState p_53572_) {
        return ItemStack.EMPTY;
    }

    protected void melt(BlockState p_54169_, Level p_54170_, BlockPos p_54171_) {
        if (p_54170_.dimensionType().ultraWarm()) {
            p_54170_.removeBlock(p_54171_, false);
        } else {
            p_54170_.setBlockAndUpdate(p_54171_, Blocks.LAVA.defaultBlockState());
            p_54170_.neighborChanged(p_54171_, Blocks.LAVA, p_54171_);
        }
    }











//
//    public MeltedCobblestone(Block.Properties properties) {
//        super(properties);
//        this.setDefaultState(this.stateContainer.getBaseState().with(AGE, Integer.valueOf(0)));
//    }
//


//    public void randomTick(BlockState state, Level worldIn, BlockPos pos, Random random) {
//        this.tick(state, worldIn, pos, random);
//    }
//
//    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
//        if ((rand.nextInt(3) == 0 || this.shouldMelt(worldIn, pos, 4)) && worldIn.getLight(pos) > 11 - state.get(AGE) - state.getOpacity(worldIn, pos) && this.slightlyMelt(state, worldIn, pos)) {
//            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
//
//            for(Direction direction : Direction.values()) {
//                blockpos$mutable.func_239622_a_(pos, direction);
//                BlockState blockstate = worldIn.getBlockState(blockpos$mutable);
//                if (blockstate.isIn(this) && !this.slightlyMelt(blockstate, worldIn, blockpos$mutable)) {
//                    worldIn.getPendingBlockTicks().scheduleTick(blockpos$mutable, this, MathHelper.nextInt(rand, 20, 40));
//                }
//            }
//
//        } else {
//            worldIn.getPendingBlockTicks().scheduleTick(pos, this, MathHelper.nextInt(rand, 20, 40));
//        }
//    }
//
//    private boolean slightlyMelt(BlockState state, World worldIn, BlockPos pos) {
//        int i = state.get(AGE);
//        if (i < 3) {
//            worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(i + 1)), 2);
//            return false;
//        } else {
//            this.turnIntoLava(state, worldIn, pos);
//            return true;
//        }
//    }
//
//    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
//        if (blockIn == this && this.shouldMelt(worldIn, pos, 2)) {
//            this.turnIntoLava(state, worldIn, pos);
//        }
//
//        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
//    }
//
//
//    private boolean shouldMelt(IBlockReader worldIn, BlockPos pos, int neighborsRequired) {
//        int i = 0;
//        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
//
//        for(Direction direction : Direction.values()) {
//            blockpos$mutable.func_239622_a_(pos, direction);
//            if (worldIn.getBlockState(blockpos$mutable).isIn(this)) {
//                ++i;
//                if (i >= neighborsRequired) {
//                    return false;
//                }
//            }
//        }
//
//        return true;
//    }
//
//    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
//        builder.add(AGE);
//    }
//
//    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
//        return ItemStack.EMPTY;
//    }
//
//    protected void turnIntoLava(BlockState p_196454_1_, World p_196454_2_, BlockPos p_196454_3_) {
//        p_196454_2_.setBlockState(p_196454_3_, Blocks.LAVA.getDefaultState());
//        p_196454_2_.neighborChanged(p_196454_3_, Blocks.LAVA, p_196454_3_);
//    }
//
//    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
//        super.harvestBlock(worldIn, player, pos, state, te, stack);
//        Material material = worldIn.getBlockState(pos.down()).getMaterial();
//        if (material.blocksMovement() || material.isLiquid()) {
//            worldIn.setBlockState(pos, Blocks.LAVA.getDefaultState());
//        }
//    }
}
