package com.maciej916.maenchants.common.registries;

import com.maciej916.maenchants.MaEnchants;
import com.maciej916.maenchants.common.block.MeltedCobblestone;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(MaEnchants.MODID)
public final class ModBlocks {

	public static Block MELTED_COBBLESTONE;

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {

		MELTED_COBBLESTONE = registerBlock(new MeltedCobblestone(), "melted_cobblestone");

	}

	public static Block registerBlock(Block block, String name) {
		BlockItem itemBlock = new BlockItem(block, new Item.Properties().tab(ModItemGroups.MOD_ITEM_GROUP));
		block.setRegistryName(name);
		itemBlock.setRegistryName(name);
		ForgeRegistries.BLOCKS.register(block);
		ForgeRegistries.ITEMS.register(itemBlock);
		return block;
	}
}