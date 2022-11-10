package com.maciej916.maenchants.common.handler;

import com.google.common.base.Objects;
import com.maciej916.maenchants.common.capabilities.player.IPlayerCapability;
import com.maciej916.maenchants.common.registries.ModBlocks;
import com.maciej916.maenchants.common.registries.ModEnchantments;
import com.maciej916.maenchants.common.util.PlayerUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.ForgeEventFactory;

public class HandlerBlazingWalker {

    public static void handlerPlayerTick(Player player) {
        IPlayerCapability enchantsCap = PlayerUtil.getAliveEnchantsCapability(player);
        if (enchantsCap == null) return;

        BlockPos blockpos = player.blockPosition();
        BlockPos lastPos = enchantsCap.getLastBlockPos();

        if (!Objects.equal(lastPos, blockpos)) {
            enchantsCap.setLastBlockPos(lastPos);

            ItemStack stack = player.getItemBySlot(EquipmentSlot.FEET);
            int lvl = stack.getEnchantmentLevel(ModEnchantments.BLAZING_WALKER.get());
            if (lvl == 0) return;

            onEntityMoved(player, player.level, blockpos, lvl);
        }
    }

    public static void onEntityMoved(LivingEntity entity, Level level, BlockPos pos, int pLevelConflicting) {
        if (entity.isOnGround()) {
            BlockState blockstate = ModBlocks.MELTED_COBBLESTONE.get().defaultBlockState();
            float f = (float)Math.min(16, 2 + pLevelConflicting);
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-f, -1.0D, -f), pos.offset(f, -1.0D, f))) {
                if (blockpos.closerToCenterThan(entity.position(), f)) {
                    blockpos$mutableblockpos.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                    BlockState blockstate1 = level.getBlockState(blockpos$mutableblockpos);
                    if (blockstate1.isAir()) {
                        BlockState blockstate2 = level.getBlockState(blockpos);
                        boolean isFull = blockstate2.getBlock() == Blocks.LAVA && blockstate2.getValue(LiquidBlock.LEVEL) == 0; //TODO: Forge, modded waters?
                        if (blockstate2.getMaterial() == Material.LAVA && isFull && blockstate.canSurvive(level, blockpos) && level.isUnobstructed(blockstate, blockpos, CollisionContext.empty()) && !ForgeEventFactory.onBlockPlace(entity, BlockSnapshot.create(level.dimension(), level, blockpos), Direction.UP)) {
                            level.setBlockAndUpdate(blockpos, blockstate);
                            level.scheduleTick(blockpos, ModBlocks.MELTED_COBBLESTONE.get(), Mth.nextInt(entity.getRandom(), 20, 60));
                        }
                    }
                }
            }
        }
    }

}
