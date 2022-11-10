package com.maciej916.maenchants.common.handler;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.maciej916.maenchants.common.capabilities.player.IPlayerCapability;
import com.maciej916.maenchants.common.client.ModKeys;
import com.maciej916.maenchants.common.network.ModNetworking;
import com.maciej916.maenchants.common.network.packet.PacketLumberjackToggle;
import com.maciej916.maenchants.common.registries.ModEnchantments;
import com.maciej916.maenchants.common.util.PlayerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.level.BlockEvent;

public class HandlerLumberjack {

    private static final HashMultimap<Player, BlockPos> treeMap = HashMultimap.create();
    private static final TagKey<Block> MINECRAFT_LOGS = BlockTags.create(new ResourceLocation("minecraft", "logs"));
    private static final TagKey<Block> FORGE_LOGS = BlockTags.create(new ResourceLocation("forge", "logs"));

    @OnlyIn(Dist.CLIENT)
    public static void handlerKeyInput(Minecraft mc, InputEvent.Key event) {
        if (!mc.isWindowActive() || mc.player == null || mc.level == null) return;

        IPlayerCapability enchantsCap = PlayerUtil.getAliveEnchantsCapability(mc.player);
        if (enchantsCap != null) {
            if (ModKeys.EXCAVATE.isDown()) {
                if (!enchantsCap.getExcavateActive()) {
                    enchantsCap.setExcavateActive(true);
                    ModNetworking.INSTANCE.sendToServer(new PacketLumberjackToggle(true));
                }
            } else {
                if (enchantsCap.getExcavateActive()) {
                    enchantsCap.setExcavateActive(false);
                    ModNetworking.INSTANCE.sendToServer(new PacketLumberjackToggle(false));
                }
            }
        }
    }

    public static void handlerBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();

        ItemStack stack = player.getItemInHand(player.getUsedItemHand());

        int lvl = stack.getEnchantmentLevel(ModEnchantments.LUMBERJACK.get());
        if (lvl == 0) return;

        IPlayerCapability enchantsCap = PlayerUtil.getAliveEnchantsCapability(player);
        if (enchantsCap == null) return;

        if (enchantsCap.getExcavateActive()) {
            BlockState state = event.getState();

            if (state.is(MINECRAFT_LOGS) || state.is(FORGE_LOGS)) {
                Level world = (Level) event.getLevel();
                BlockPos pos = event.getPos();
                findTree(player, world, pos, state);

                if (treeMap.get(player).size() < 512) {
                    doBreak(player, world, pos);
                    event.setCanceled(true);
                } else {
                    treeMap.asMap().remove(player);
                }
            }
        } else {
            treeMap.asMap().remove(player);
        }
    }

    public static void doBreak(Player player, Level world, BlockPos pos) {
        int logsBreak = 0;
        Block block = world.getBlockState(pos).getBlock();

        InteractionHand hand = player.getUsedItemHand();
        ItemStack stack = player.getItemInHand(hand);

        for (BlockPos point : ImmutableSet.copyOf(treeMap.get(player))) {
            if (stack.getDamageValue() >= 0) {
                logsBreak++;
                world.setBlock(point, Blocks.AIR.defaultBlockState(), 3);
                stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));
            } else {
                break;
            }
        }

        ItemEntity item = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(block, logsBreak));
        world.addFreshEntity(item);

        treeMap.asMap().remove(player);
    }

    private static void findTree(Player player, Level world, BlockPos mine, BlockState state) {
        for (int offsetX = -1; offsetX <= 1; offsetX++) {
            for (int offsetZ = -1; offsetZ <= 1; offsetZ++) {
                for (int offsetY = -1; offsetY <= 1; offsetY++) {
                    BlockPos newPoint = mine.offset(offsetX, offsetY, offsetZ);
                    if (treeMap.containsEntry(player, newPoint)) continue;

                    BlockState newBlockState = world.getBlockState(newPoint);
                    boolean isLeaves = newBlockState.getMaterial() == Material.LEAVES;

                    if (newBlockState.getBlock() == state.getBlock() && !isLeaves) {
                        treeMap.put(player, newPoint);
                        findTree(player, world, newPoint, state);
                    }
                }
            }
        }
    }

}