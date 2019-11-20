package com.maciej916.maenchants.config;

import net.minecraftforge.fml.config.ModConfig;

public final class ConfigHelper {

	private static ModConfig serverConfig;

	public static void bake(final ModConfig config) {
		serverConfig = config;

		ConfigValues.reinforced_tip = ConfigHolder.SERVER.reinforced_tip.get();
		ConfigValues.stone_mending = ConfigHolder.SERVER.stone_mending.get();
		ConfigValues.lumberjack = ConfigHolder.SERVER.lumberjack.get();
		ConfigValues.momentum = ConfigHolder.SERVER.momentum.get();

		ConfigValues.true_shot = ConfigHolder.SERVER.true_shot.get();
		ConfigValues.quick_draw = ConfigHolder.SERVER.quick_draw.get();
		ConfigValues.floating = ConfigHolder.SERVER.floating.get();
		ConfigValues.paralysis = ConfigHolder.SERVER.paralysis.get();

		ConfigValues.combo = ConfigHolder.SERVER.combo.get();
		ConfigValues.faster_attack = ConfigHolder.SERVER.faster_attack.get();
		ConfigValues.lifesteal = ConfigHolder.SERVER.lifesteal.get();
		ConfigValues.ice_aspect = ConfigHolder.SERVER.ice_aspect.get();
		ConfigValues.ice_aspect = ConfigHolder.SERVER.ice_aspect.get();
		ConfigValues.wisdom = ConfigHolder.SERVER.wisdom.get();

		ConfigValues.blazing_walker = ConfigHolder.SERVER.blazing_walker.get();
		ConfigValues.step_assist = ConfigHolder.SERVER.step_assist.get();
		ConfigValues.night_vision = ConfigHolder.SERVER.night_vision.get();
		ConfigValues.multi_jump = ConfigHolder.SERVER.multi_jump.get();
	}

	public static void setValueAndSave(final ModConfig modConfig, final String path, final Object newValue) {
		modConfig.getConfigData().set(path, newValue);
		modConfig.save();
	}
}
