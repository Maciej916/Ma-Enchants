package com.maciej916.maenchants.common.config.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {

	public static ForgeConfigSpec register(ForgeConfigSpec.Builder builder) {
		builder.push("general");

		builder.pop();

		return builder.build();
	}
}
