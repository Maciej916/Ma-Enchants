package com.maciej916.maenchants;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;

@EventBusSubscriber(modid = MaEnchants.MODID, bus = EventBusSubscriber.Bus.MOD)
public final class ModEventSubscriber {

	@SubscribeEvent
	public static void onModConfigEvent(final ModConfig.ModConfigEvent event) {
//		final ModConfig config = event.getConfig();
//		if (config.getSpec() == ConfigHolder.CLIENT_SPEC) {
//			ConfigHelper.bakeClient(config);
//			Log.debug("Baked client config");
//		} else if (config.getSpec() == ConfigHolder.SERVER_SPEC) {
//			ConfigHelper.bakeServer(config);
//			Log.debug("Baked server config");
//		}
	}


}
