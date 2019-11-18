package com.maciej916.maenchants;

import com.google.common.base.Preconditions;
import com.maciej916.maenchants.block.MeltedCobblestone;
import com.maciej916.maenchants.config.ConfigHelper;
import com.maciej916.maenchants.enchantment.*;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.jline.utils.Log;

import javax.annotation.Nonnull;

import static com.maciej916.maenchants.MaEnchants.MODID;
import static com.maciej916.maenchants.init.ModItemGroups.MOD_ITEM_GROUP;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public final class ModEventSubscriber {

	@SubscribeEvent
	public static void onModConfigEvent(final ModConfig.ModConfigEvent event) {
		final ModConfig config = event.getConfig();
		ConfigHelper.bake(config);
		Log.debug("Baked config");
	}

	@SubscribeEvent
	public static void onRegisterBlocks(final RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(
			setup(new MeltedCobblestone(Block.Properties.create(Material.IRON, MaterialColor.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.STONE)), "melted_cobblestone")
		);
	}

	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		for (final Block block : ForgeRegistries.BLOCKS.getValues()) {
			final ResourceLocation blockRegistryName = block.getRegistryName();
			Preconditions.checkNotNull(blockRegistryName, "Registry Name of Block \"" + block + "\" of class \"" + block.getClass().getName() + "\"is null! This is not allowed!");
			if (!blockRegistryName.getNamespace().equals(MODID)) {
				continue;
			}
			final Item.Properties properties = new Item.Properties().group(MOD_ITEM_GROUP);
			final BlockItem blockItem = new BlockItem(block, properties);
			registry.register(setup(blockItem, blockRegistryName));
		}
	}

	@SubscribeEvent
	public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
		final IForgeRegistry<Enchantment> registry = event.getRegistry();
		registry.registerAll(
				// Tools
				setup(new EnchantmentReinforcedTip(), "reinforced_tip"),
				setup(new EnchantmentStoneMending(), "stone_mending"),
				setup(new EnchantmentLumberjack(), "lumberjack"),
				setup(new EnchantmentMomentum(), "momentum"),

				// Bows
				setup(new EnchantmentTrueShot(), "true_shot"),
				setup(new EnchantmentQuickDraw(), "quick_draw"),
				setup(new EnchantmentFloating(), "floating"),
				setup(new EnchantmentParalysis(), "paralysis"),

				// Swords
				setup(new EnchantmentCombo(), "combo"),
				setup(new EnchantmentFasterAttack(), "faster_attack"),
				setup(new EnchantmentLifesteal(), "lifesteal"),
				setup(new EnchantmentIceAspect(), "ice_aspect"),

				// Armour
				setup(new EnchantmentBlazingWalker(), "blazing_walker"),
				setup(new EnchantmentStepAssist(), "step_assist"),
				setup(new EnchantmentNightVision(), "night_vision"),
				setup(new EnchantmentMultiJump(), "multi_jump")
		);
	}

	@Nonnull
	private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final String name) {
		return setup(entry, new ResourceLocation(MODID, name));
	}

	@Nonnull
	private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final ResourceLocation registryName) {
		Preconditions.checkNotNull(entry, "Entry cannot be null!");
		Preconditions.checkNotNull(registryName, "Registry name to assign to entry cannot be null!");
		entry.setRegistryName(registryName);
		return entry;
	}
}