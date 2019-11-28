package com.maciej916.maenchants;

import com.maciej916.maenchants.handler.HandlerCombo;
import com.maciej916.maenchants.handler.HandlerLumberjack;
import com.maciej916.maenchants.handler.HandlerMomentum;
import com.maciej916.maenchants.handler.HandlerMultiJump;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import static com.maciej916.maenchants.MaEnchants.proxy;

@EventBusSubscriber(modid = MaEnchants.MODID, bus = EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public final class ForgeEventSubscriberClient {

    @SubscribeEvent
    public static void onMiss(PlayerInteractEvent.LeftClickEmpty event) {
        HandlerCombo.handlerMiss(event);
    }

    @SubscribeEvent
    public static void onHitBlock(PlayerInteractEvent.LeftClickBlock event) {
        HandlerCombo.handlerHitBlock(event);
    }

    @SubscribeEvent
    public static void onClientKeyInput(InputEvent.KeyInputEvent event) {
        Minecraft mc = proxy.getClient();
        if (!mc.isGameFocused() || mc.world == null) return;

        HandlerMultiJump.handlerKeyInput(mc, event);
        HandlerLumberjack.handlerKeyInput(mc, event);
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        HandlerCombo.handlerTooltip(event);
        HandlerMomentum.handlerTooltip(event);
    }

}