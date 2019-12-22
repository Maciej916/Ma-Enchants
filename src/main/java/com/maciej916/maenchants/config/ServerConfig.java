package com.maciej916.maenchants.config;

import net.minecraftforge.common.ForgeConfigSpec;

final class ServerConfig {

	// Enchants
	final ForgeConfigSpec.BooleanValue curse_breaking;
	final ForgeConfigSpec.BooleanValue curse_butterfingers;

	final ForgeConfigSpec.BooleanValue reinforced_tip;
	final ForgeConfigSpec.BooleanValue stone_mending;
	final ForgeConfigSpec.BooleanValue lumberjack;
	final ForgeConfigSpec.BooleanValue momentum;
	final ForgeConfigSpec.BooleanValue butchering;

	final ForgeConfigSpec.BooleanValue true_shot;
	final ForgeConfigSpec.BooleanValue quick_draw;
	final ForgeConfigSpec.BooleanValue floating;
	final ForgeConfigSpec.BooleanValue paralysis;

	final ForgeConfigSpec.BooleanValue combo;
	final ForgeConfigSpec.BooleanValue faster_attack;
	final ForgeConfigSpec.BooleanValue lifesteal;
	final ForgeConfigSpec.BooleanValue ice_aspect;
	final ForgeConfigSpec.BooleanValue wisdom;

	final ForgeConfigSpec.BooleanValue blazing_walker;
	final ForgeConfigSpec.BooleanValue step_assist;
	final ForgeConfigSpec.BooleanValue night_vision;
	final ForgeConfigSpec.BooleanValue multi_jump;

	ServerConfig(final ForgeConfigSpec.Builder builder) {
		builder.push("curse_breaking");
		curse_breaking = builder.define("enable", true);
		builder.pop();

		builder.push("curse_butterfingers");
		curse_butterfingers = builder.define("enable", true);
		builder.pop();


		builder.push("reinforced_tip");
		reinforced_tip = builder.define("enable", true);
		builder.pop();

		builder.push("stone_mending");
		stone_mending = builder.define("enable", true);
		builder.pop();

		builder.push("lumberjack");
		lumberjack = builder.define("enable", true);
		builder.pop();

		builder.push("momentum");
		momentum = builder.define("enable", true);
		builder.pop();

		builder.push("butchering");
		butchering = builder.define("enable", true);
		builder.pop();


		builder.push("true_shot");
		true_shot = builder.define("enable", true);
		builder.pop();

		builder.push("quick_draw");
		quick_draw = builder.define("enable", true);
		builder.pop();

		builder.push("floating");
		floating = builder.define("enable", true);
		builder.pop();

		builder.push("paralysis");
		paralysis = builder.define("enable", true);
		builder.pop();


		builder.push("combo");
		combo = builder.define("enable", true);
		builder.pop();

		builder.push("faster_attack");
		faster_attack = builder.define("enable", true);
		builder.pop();

		builder.push("lifesteal");
		lifesteal = builder.define("enable", true);
		builder.pop();

		builder.push("ice_aspect");
		ice_aspect = builder.define("enable", true);
		builder.pop();

		builder.push("wisdom");
		wisdom = builder.define("enable", true);
		builder.pop();


		builder.push("blazing_walker");
		blazing_walker = builder.define("enable", true);
		builder.pop();

		builder.push("step_assist");
		step_assist = builder.define("enable", true);
		builder.pop();

		builder.push("night_vision");
		night_vision = builder.define("enable", true);
		builder.pop();

		builder.push("multi_jump");
		multi_jump = builder.define("enable", true);
		builder.pop();
	}
}