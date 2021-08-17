package com.maciej916.maenchants.common.config;

import net.minecraftforge.fml.config.ModConfig;

public final class ConfigHelper {

	private static ModConfig serverConfig;

	public static void bake(final ModConfig config) {
		serverConfig = config;

		ConfigValues.curse_breaking = ConfigHolder.COMMON.curse_breaking.get();
		ConfigValues.curse_butterfingers = ConfigHolder.COMMON.curse_butterfingers.get();
		ConfigValues.curse_aquaphobia = ConfigHolder.COMMON.curse_aquaphobia.get();

		ConfigValues.curse_death = ConfigHolder.COMMON.curse_death.get();

		ConfigValues.reinforced_tip = ConfigHolder.COMMON.reinforced_tip.get();
		ConfigValues.stone_mending = ConfigHolder.COMMON.stone_mending.get();
		ConfigValues.lumberjack = ConfigHolder.COMMON.lumberjack.get();
		ConfigValues.momentum = ConfigHolder.COMMON.momentum.get();
		ConfigValues.butchering = ConfigHolder.COMMON.butchering.get();

		ConfigValues.true_shot = ConfigHolder.COMMON.true_shot.get();
		ConfigValues.quick_draw = ConfigHolder.COMMON.quick_draw.get();
		ConfigValues.floating = ConfigHolder.COMMON.floating.get();
		ConfigValues.paralysis = ConfigHolder.COMMON.paralysis.get();
		ConfigValues.detonation = ConfigHolder.COMMON.detonation.get();

		ConfigValues.combo = ConfigHolder.COMMON.combo.get();
		ConfigValues.faster_attack = ConfigHolder.COMMON.faster_attack.get();
		ConfigValues.lifesteal = ConfigHolder.COMMON.lifesteal.get();
		ConfigValues.ice_aspect = ConfigHolder.COMMON.ice_aspect.get();
		ConfigValues.ice_aspect = ConfigHolder.COMMON.ice_aspect.get();
		ConfigValues.wisdom = ConfigHolder.COMMON.wisdom.get();

		ConfigValues.blazing_walker = ConfigHolder.COMMON.blazing_walker.get();
		ConfigValues.step_assist = ConfigHolder.COMMON.step_assist.get();
		ConfigValues.night_vision = ConfigHolder.COMMON.night_vision.get();
		ConfigValues.multi_jump = ConfigHolder.COMMON.multi_jump.get();
		ConfigValues.soft_fall = ConfigHolder.COMMON.soft_fall.get();

		ConfigValues.timeless = ConfigHolder.COMMON.timeless.get();
	}

	public static void setValueAndSave(final ModConfig modConfig, final String path, final Object newValue) {
		modConfig.getConfigData().set(path, newValue);
		modConfig.save();
	}
}
