package com.maciej916.maenchants.common.registries;

import com.maciej916.maenchants.common.block.MeltedCobblestone;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModBlocks {

	public static BlockItem MELTED_COBBLESTONE;

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		MELTED_COBBLESTONE = registerBlock(new MeltedCobblestone(), "melted_cobblestone");
	}

	public static BlockItem registerBlock(BlockItem blockItem, String name) {
		return registerBlock(blockItem.getBlock(), blockItem, name);
	}

	public static BlockItem registerBlock(Block block, String name) {
		return registerBlock(block, new BlockItem(block, new Item.Properties()), name);
	}

	public static BlockItem registerBlock(Block block, BlockItem blockItem, String name) {
		block.setRegistryName(name);
		blockItem.setRegistryName(name);
		ForgeRegistries.BLOCKS.register(block);
		ForgeRegistries.ITEMS.register(blockItem);
		return blockItem;
	}
}