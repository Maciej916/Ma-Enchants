package com.maciej916.maenchants;

import com.maciej916.maenchants.enchantment.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

import static com.maciej916.maenchants.utils.Enchants.*;

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

	@SubscribeEvent
	public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
		Set<Enchantment> enchants = new HashSet<>();

		// Tools
		enchants.add(REINFORCED_TIP);
		enchants.add(STONE_MENDING);
		enchants.add(LUMBERJACK);
		enchants.add(MOMENTUM);

		// Bows
		enchants.add(TRUE_SHOT);
		enchants.add(QUICK_DRAW);
		enchants.add(FLOATING);
		enchants.add(PARALYSIS);

		// Swords
		enchants.add(COMBO);
		enchants.add(FASTER_ATTACK);
		enchants.add(LIFESTEAL);
		enchants.add(ICE_ASPECT);

		IForgeRegistry<Enchantment> r = event.getRegistry();
		for (Enchantment enchant : enchants) {
			r.register(enchant);
		}
	}

}
