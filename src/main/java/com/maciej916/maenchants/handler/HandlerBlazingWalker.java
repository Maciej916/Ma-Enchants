package com.maciej916.maenchants.handler;

import com.google.common.base.Objects;
import com.maciej916.maenchants.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.World;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.Iterator;

import static com.maciej916.maenchants.init.ModEnchants.BLAZING_WALKER;
import static com.maciej916.maenchants.init.ModEnchants.FLOATING;

public class HandlerBlazingWalker {

    public static void handlerPlayerTick(PlayerEntity player) {
        ItemStack stack = player.getItemStackFromSlot(EquipmentSlotType.FEET);
        int lvl = EnchantmentHelper.getEnchantmentLevel(BLAZING_WALKER, stack);
        if (lvl == 0) return;

        BlockPos prevBlockpos = ObfuscationReflectionHelper.getPrivateValue(LivingEntity.class, player, "field_184620_bC");
        BlockPos blockpos = new BlockPos(player);

        if (!Objects.equal(prevBlockpos, blockpos)) {
            makeFloor(player, player.world, blockpos, lvl);
        }
    }

    private static void makeFloor(LivingEntity living, World worldIn, BlockPos pos, int level) {
        if (living.onGround) {
            BlockState blockstate = ModBlocks.MELTED_COBBLESTONE.getDefaultState();
            float f = (float)Math.min(16, 2 + level);
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
            Iterator var7 = BlockPos.getAllInBoxMutable(pos.add((double)(-f), -1.0D, (double)(-f)), pos.add((double)f, -1.0D, (double)f)).iterator();

            while(true) {
                BlockPos blockpos;
                BlockState blockstate1;
                do {
                    do {
                        if (!var7.hasNext()) {
                            return;
                        }

                        blockpos = (BlockPos)var7.next();
                    } while(!blockpos.withinDistance(living.getPositionVec(), (double)f));

                    blockpos$mutable.setPos(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                    blockstate1 = worldIn.getBlockState(blockpos$mutable);
                } while(!blockstate1.isAir(worldIn, blockpos$mutable));

                BlockState blockstate2 = worldIn.getBlockState(blockpos);
                boolean isFull = blockstate2.getBlock() == Blocks.LAVA && (Integer)blockstate2.get(FlowingFluidBlock.LEVEL) == 0;
                if (blockstate2.getMaterial() == Material.LAVA && isFull && blockstate.isValidPosition(worldIn, blockpos) && worldIn.func_226663_a_(blockstate, blockpos, ISelectionContext.dummy()) && !ForgeEventFactory.onBlockPlace(living, new BlockSnapshot(worldIn, blockpos, blockstate2), Direction.UP)) {
                    worldIn.setBlockState(blockpos, blockstate);
                    worldIn.getPendingBlockTicks().scheduleTick(blockpos, ModBlocks.MELTED_COBBLESTONE, MathHelper.nextInt(living.getRNG(), 20, 60));
                }
            }
        }
    }

}
