package com.maciej916.maenchants.common.subscribers;

import com.maciej916.maenchants.common.handler.*;
import com.maciej916.maenchants.common.registries.ModCapabilities;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = com.maciej916.maenchants.MaEnchants.MODID, bus = EventBusSubscriber.Bus.FORGE)
public final class ForgeEventSubscriber {

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        event.getLevel().getCapability(ModCapabilities.LEVEL_CAPABILITY).ifPresent(iLevelCapability -> iLevelCapability.entityJoinLevelEvent(event));
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {

        Player player = event.player;
        Level level = player.getCommandSenderWorld();

        if (!level.isClientSide()) {
            HandlerBlazingWalker.handlerPlayerTick(player);
            HandlerCurseAquaphobia.handlerPlayerTick(event);
            HandlerCurseDeath.handlerPlayerTick(event);
        } else {
            HandlerMultiJump.handlerPlayerTick(event);
            HandlerNightVision.handlerPlayerTick(player);
            HandlerStepAssist.handlerPlayerTick(player);
            HandlerQuickDraw.handlerPlayerTick(player);
        }

        HandlerFasterAttack.handlerPlayerTick(player);
    }

    @SubscribeEvent
    public static void onHurt(LivingHurtEvent event) {
        HandlerLifesteal.handlerHurt(event);
        HandlerFloating.handlerHurt(event);
        HandlerParalysis.handlerHurt(event);
        HandlerIceAspect.handlerHurt(event);
        HandlerCurseButterfingers.handlerHurt(event);
        HandlerCurseBreaking.handlerHurt(event);
        HandlerButchering.handlerHurt(event);
        HandlerDetonation.handlerHurt(event);
    }

    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event) {
//        HandlerFasterAttack.handlerAttack(event);
    }

    @SubscribeEvent
    public static void onFall(LivingFallEvent event) {
        HandlerSoftFall.handlerFall(event);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onBreak(BlockEvent.BreakEvent event) {
        HandlerLumberjack.handlerBreak(event);
        HandlerMomentum.handlerBreak(event);
        HandlerStoneMending.handlerBreak(event);
        HandlerCurseBreaking.handlerBreak(event);
        HandlerCurseButterfingers.handlerBreak(event);
    }

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        HandlerMomentum.handlerSpeed(event);
        HandlerReinforcedTip.handlerSpeed(event);
    }

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPlayer serverPlayer = (ServerPlayer) event.getEntity();
        HandlerMultiJump.handlerLoggedIn(serverPlayer);
    }

    @SubscribeEvent
    public static void onItemDespawn(ItemExpireEvent event) {
        HandlerTimeless.handlerItemExpire(event);
    }

    @SubscribeEvent
    public static void onExpDrop(LivingExperienceDropEvent event) {
        HandlerWisdom.handlerExpDrop(event);
    }

}