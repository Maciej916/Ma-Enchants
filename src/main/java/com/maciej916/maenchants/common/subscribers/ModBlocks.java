package com.maciej916.maenchants.common.subscribers;

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

    public static Block MELTED_COBBLESTONE;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        MELTED_COBBLESTONE = registerBlock(new MeltedCobblestone(), "melted_cobblestone");
    }

    public static Block registerBlock(Block block, String name) {
//        BlockItem itemBlock = new BlockItem(block, new Item.Properties().tab(MOD_ITEM_GROUP));
        BlockItem itemBlock = new BlockItem(block, new Item.Properties());
        block.setRegistryName(name);
        itemBlock.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(itemBlock);
        return block;
    }

}
