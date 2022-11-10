package com.maciej916.maenchants.common.client;

import com.maciej916.maenchants.MaEnchants;
import com.maciej916.maenchants.common.handler.HandlerCombo;
import com.maciej916.maenchants.common.handler.HandlerLumberjack;
import com.maciej916.maenchants.common.handler.HandlerMomentum;
import com.maciej916.maenchants.common.handler.HandlerMultiJump;
import com.maciej916.maenchants.common.proxy.ModProxy;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = MaEnchants.MODID, bus = EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public final class ClientForgeEventSubscriber {

    @SubscribeEvent
    public static void onMiss(PlayerInteractEvent.LeftClickEmpty event) {
        HandlerCombo.handlerMiss(event);
    }

    @SubscribeEvent
    public static void onHitBlock(PlayerInteractEvent.LeftClickBlock event) {
        HandlerCombo.handlerHitBlock(event);
    }

    @SubscribeEvent
    public static void clientKeyInput(InputEvent.Key event) {
        Minecraft minecraft = ModProxy.PROXY.getClient();


        HandlerMultiJump.handlerKeyInput(minecraft, event);
        HandlerLumberjack.handlerKeyInput(minecraft, event);
    }


    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        HandlerCombo.handlerTooltip(event);
        HandlerMomentum.handlerTooltip(event);
    }

}