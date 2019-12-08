package com.maciej916.maenchants;

import com.maciej916.maenchants.capabilities.EnchantsProvider;
import com.maciej916.maenchants.capabilities.IEnchants;
import com.maciej916.maenchants.handler.*;
import com.maciej916.maenchants.utils.PlayerUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = MaEnchants.MODID, bus = EventBusSubscriber.Bus.FORGE)
public final class ForgeEventSubscriber {

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof PlayerEntity) {
            event.addCapability(new ResourceLocation(MaEnchants.MODID, "enchants"), new EnchantsProvider());
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        IEnchants origEnchantsCap = PlayerUtil.getEnchantsCapability(event.getOriginal());
        IEnchants enchantsCap = PlayerUtil.getEnchantsCapability(event.getPlayer());

        enchantsCap.setVersion(origEnchantsCap.getVersion());
        enchantsCap.setStepAssist(origEnchantsCap.getStepAssist());
        enchantsCap.setNightVision(origEnchantsCap.getNightVision());
        enchantsCap.setMultiJump(origEnchantsCap.getMultiJump());
        enchantsCap.setMultiJumpSpace(origEnchantsCap.getMultiJumpSpace());
        enchantsCap.setExcavateActive(origEnchantsCap.getExcavateActive());

        System.out.println(enchantsCap);
    }

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

}