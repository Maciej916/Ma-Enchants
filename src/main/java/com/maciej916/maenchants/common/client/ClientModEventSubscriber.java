package com.maciej916.maenchants.common.client;

import com.maciej916.maenchants.MaEnchants;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MaEnchants.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEventSubscriber {

	@SubscribeEvent
	public static void onKeyRegister(RegisterKeyMappingsEvent event) {
		event.register(ModKeys.EXCAVATE);
	}

}
