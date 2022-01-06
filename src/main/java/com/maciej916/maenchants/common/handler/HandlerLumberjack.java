package com.maciej916.maenchants.common.handler;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.maciej916.maenchants.client.Keys;
import com.maciej916.maenchants.common.capabilities.player.IPlayerCapability;
import com.maciej916.maenchants.common.network.ModNetworking;
import com.maciej916.maenchants.common.network.packet.PacketLumberjackToggle;
import com.maciej916.maenchants.common.util.PlayerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.world.BlockEvent;

import static com.maciej916.maenchants.common.registries.ModEnchants.LUMBERJACK;

public class HandlerLumberjack {

    private static final HashMultimap<Player, BlockPos> treeMap = HashMultimap.create();

    @OnlyIn(Dist.CLIENT)
    public static void handlerKeyInput(Minecraft mc, InputEvent.KeyInputEvent event) {
        IPlayerCapability enchantsCap = PlayerUtil.getAliveEnchantsCapability(mc.player);
        if (enchantsCap == null) return;

        boolean down = Keys.excavateKey.isDown();
        if (down) {
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

    public static void handlerBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();

        InteractionHand hand = player.getUsedItemHand();
        if (hand == null) return;

        ItemStack stack = player.getItemInHand(hand);
        if (EnchantmentHelper.getItemEnchantmentLevel(LUMBERJACK, stack) == 0) return;

        IPlayerCapability enchantsCap = PlayerUtil.getAliveEnchantsCapability(player);
        if (enchantsCap == null) return;

        if (enchantsCap.getExcavateActive()) {
            BlockState state = event.getState();
            Block block = state.getBlock();

            ResourceLocation logTagMC = new ResourceLocation("minecraft", "logs");
            ResourceLocation logTagForge = new ResourceLocation("forge", "logs");
            if (!block.getTags().contains(logTagMC) && !block.getTags().contains(logTagForge)) return;

            Level world = (Level) event.getWorld();
            BlockPos pos = event.getPos();
            findTree(player, world, pos, state);

            if (treeMap.get(player).size() < 512) {
                doBreak(player, world, pos);
                event.setCanceled(true);
            } else {
                treeMap.asMap().remove(player);
            }
        } else {
            treeMap.asMap().remove(player);
        }
    }

    public static void doBreak(Player player, Level world, BlockPos pos) {
        int logsBreak = 0;
        Block block = world.getBlockState(pos).getBlock();

        InteractionHand hand = player.getUsedItemHand();
        if (hand == null) return;

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