package com.maciej916.maenchants;

import com.maciej916.maenchants.handler.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = MaEnchants.MODID, bus = EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public final class ForgeEventSubscriberClient {

    @SubscribeEvent
    public static void onHitBlock(PlayerInteractEvent.LeftClickBlock event) {
        HandlerCombo.handlerHitBlock(event);
    }

    @SubscribeEvent
    public static void onClientKeyInput(InputEvent.KeyInputEvent event) {
        HandlerMultiJump.handlerKeyInput(event);
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        HandlerCombo.handlerTooltip(event);
        HandlerMomentum.handlerTooltip(event);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onBreak(BlockEvent.BreakEvent event) {
        HandlerLumberjack.handlerBreak(event);
    }

}