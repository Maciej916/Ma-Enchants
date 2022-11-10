package com.maciej916.maenchants.common.registries;

import com.google.common.base.Supplier;
import com.maciej916.maenchants.common.block.MeltedCobblestone;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.maciej916.maenchants.MaEnchants.MODID;

public final class ModBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

	public static final RegistryObject<Block> MELTED_COBBLESTONE = registerBlock("melted_cobblestone", MeltedCobblestone::new);

	private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
		return BLOCKS.register(name, block);
	}

	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}
}