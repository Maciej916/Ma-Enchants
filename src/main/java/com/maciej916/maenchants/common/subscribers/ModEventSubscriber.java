package com.maciej916.maenchants.common.subscribers;

import com.google.common.base.Preconditions;
import com.maciej916.maenchants.common.block.MeltedCobblestone;
import com.maciej916.maenchants.common.config.ConfigHelper;
import com.maciej916.maenchants.common.curse.CurseAquaphobia;
import com.maciej916.maenchants.common.curse.CurseBreaking;
import com.maciej916.maenchants.common.curse.CurseButterfingers;
import com.maciej916.maenchants.common.curse.CurseDeath;
import com.maciej916.maenchants.common.enchantment.*;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.jline.utils.Log;

import javax.annotation.Nonnull;

import static com.maciej916.maenchants.MaEnchants.MODID;

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
	public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
		final IForgeRegistry<Enchantment> registry = event.getRegistry();
		registry.registerAll(
				// All
				setup(new CurseBreaking(), "curse_breaking"),
				setup(new CurseButterfingers(), "curse_butterfingers"),
				setup(new CurseAquaphobia(), "curse_aquaphobia"),
				setup(new CurseDeath(), "curse_death"),

				// Tools
				setup(new EnchantmentReinforcedTip(), "reinforced_tip"),
				setup(new EnchantmentStoneMending(), "stone_mending"),
				setup(new EnchantmentLumberjack(), "lumberjack"),
				setup(new EnchantmentMomentum(), "momentum"),
				setup(new EnchantmentButchering(), "butchering"),

				// Bows
				setup(new EnchantmentTrueShot(), "true_shot"),
				setup(new EnchantmentQuickDraw(), "quick_draw"),
				setup(new EnchantmentFloating(), "floating"),
				setup(new EnchantmentParalysis(), "paralysis"),
				setup(new EnchantmentDetonation(), "detonation"),

				// Swords
				setup(new EnchantmentCombo(), "combo"),
				setup(new EnchantmentFasterAttack(), "faster_attack"),
				setup(new EnchantmentLifesteal(), "lifesteal"),
				setup(new EnchantmentIceAspect(), "ice_aspect"),
				setup(new EnchantmentWisdom(), "wisdom"),

				// Armour
				setup(new EnchantmentBlazingWalker(), "blazing_walker"),
				setup(new EnchantmentStepAssist(), "step_assist"),
				setup(new EnchantmentNightVision(), "night_vision"),
				setup(new EnchantmentMultiJump(), "multi_jump"),

				// All
				setup(new EnchantmentTimeless(), "timeless")
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