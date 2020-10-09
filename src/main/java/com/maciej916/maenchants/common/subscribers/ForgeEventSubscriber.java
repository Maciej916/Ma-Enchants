package com.maciej916.maenchants.common.subscribers;

import com.maciej916.maenchants.common.handler.HandlerLumberjack;
import com.maciej916.maenchants.common.handler.HandlerMomentum;
import com.maciej916.maenchants.common.handler.HandlerMultiJump;
import com.maciej916.maenchants.common.handler.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = com.maciej916.maenchants.MaEnchants.MODID, bus = EventBusSubscriber.Bus.FORGE)
public final class ForgeEventSubscriber {

    @SubscribeEvent
    public static void worldTick(TickEvent.ServerTickEvent event) {
        HandlerTrueShot.handlerWorldTick(event);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {

        PlayerEntity player = event.player;
        World world = player.world;

        if (!world.isRemote()) {
            HandlerBlazingWalker.handlerPlayerTick(player);
            HandlerCurseAquaphobia.handlerPlayerTick(event);
            HandlerCurseDeath.handlerPlayerTick(event);
        } else {
            HandlerMultiJump.handlerPlayerTick(event);
            HandlerNightVision.handlerPlayerTick(player);
            HandlerStepAssist.handlerPlayerTick(player);
            HandlerFasterAttack.handlerPlayerTick(player);
            HandlerQuickDraw.handlerPlayerTick(player);
        }
    }

    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        HandlerLifesteal.handlerAttack(event);
        HandlerFloating.handlerAttack(event);
        HandlerParalysis.handlerAttack(event);
        HandlerIceAspect.handlerAttack(event);
        HandlerCurseButterfingers.handlerAttack(event);
        HandlerCurseBreaking.handlerAttack(event);
        HandlerButchering.handlerAttack(event);
        HandlerDetonation.handlerAttack(event);
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
    public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
        HandlerTrueShot.handlerSpawn(event);
    }

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) event.getPlayer();

        HandlerMultiJump.handlerLoggedIn(serverPlayer);
    }

    @SubscribeEvent
    public static void onItemDespawn(ItemExpireEvent event) {
        HandlerTimeless.handlerItemExpire(event);
    }

}