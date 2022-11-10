package com.maciej916.maenchants.common.config.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public final class ServerConfig {

	// Curses
	public static ForgeConfigSpec.BooleanValue curse_breaking;
	public static ForgeConfigSpec.BooleanValue curse_butterfingers;
	public static ForgeConfigSpec.BooleanValue curse_aquaphobia;
	public static ForgeConfigSpec.BooleanValue curse_death;

	// Enchants
	public static ForgeConfigSpec.BooleanValue reinforced_tip;
	public static ForgeConfigSpec.BooleanValue stone_mending;
	public static ForgeConfigSpec.BooleanValue lumberjack;
	public static ForgeConfigSpec.BooleanValue momentum;
	public static ForgeConfigSpec.BooleanValue butchering;

	public static ForgeConfigSpec.BooleanValue true_shot;
	public static ForgeConfigSpec.BooleanValue quick_draw;
	public static ForgeConfigSpec.BooleanValue floating;
	public static ForgeConfigSpec.BooleanValue paralysis;
	public static ForgeConfigSpec.BooleanValue detonation;

	public static ForgeConfigSpec.BooleanValue combo;
	public static ForgeConfigSpec.BooleanValue faster_attack;
	public static ForgeConfigSpec.BooleanValue lifesteal;
	public static ForgeConfigSpec.BooleanValue ice_aspect;
	public static ForgeConfigSpec.BooleanValue wisdom;

	public static ForgeConfigSpec.BooleanValue blazing_walker;
	public static ForgeConfigSpec.BooleanValue step_assist;
	public static ForgeConfigSpec.BooleanValue night_vision;
	public static ForgeConfigSpec.BooleanValue multi_jump;
	public static ForgeConfigSpec.BooleanValue soft_fall;

	public static ForgeConfigSpec.BooleanValue timeless;

	public static ForgeConfigSpec register(ForgeConfigSpec.Builder builder) {
		builder.push("general");

		builder.pop();


		builder.push("curse_breaking");
		curse_breaking = builder.define("enable", true);
		builder.pop();

		builder.push("curse_butterfingers");
		curse_butterfingers = builder.define("enable", true);
		builder.pop();

		builder.push("curse_aquaphobia");
		curse_aquaphobia = builder.define("enable", true);
		builder.pop();

		builder.push("curse_death");
		curse_death = builder.define("enable", true);
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

		builder.push("detonation");
		detonation = builder.define("enable", true);
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
		builder.pop();;

		builder.push("soft_fall");
		soft_fall = builder.define("enable", true);
		builder.pop();;

		builder.push("timeless");
		timeless = builder.define("enable", true);
		builder.pop();

		return builder.build();
	}



}
