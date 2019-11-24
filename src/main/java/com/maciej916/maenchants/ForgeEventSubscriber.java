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
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
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
    }

    @SubscribeEvent
    public static void worldTick(TickEvent.ServerTickEvent event) {
        HandlerTrueShot.handlerWorldTick(event);
    }

    @SubscribeEvent
    public static void onPlayerUpdate(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;
        World world = event.player.world;
        try {
            HandlerBlazingWalker.handlerUpdate(player, world);
            HandlerFasterAttack.handlerUpdate(player);
            HandlerQuickDraw.handlerUpdate(player);
            HandlerStepAssist.handlerUpdate(player, world);
            HandlerNightVision.handlerUpdate(player, world);
            HandlerMultiJump.handlerUpdate(event);
        } catch (Exception ignored) {}
    }

    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        HandlerLifesteal.handlerAttack(event);
    }

    @SubscribeEvent
    public static void onExperienceDrop(LivingExperienceDropEvent event) {
        HandlerWisdom.handlerExpDrop(event);
    }

    @SubscribeEvent
    public static void onMiss(PlayerInteractEvent.LeftClickEmpty event) {
        HandlerCombo.handlerMiss(event);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onBreak(BlockEvent.BreakEvent event) {
        HandlerLumberjack.handlerBreak(event);
        HandlerMomentum.handlerBreak(event);
        HandlerStoneMending.handlerBreak(event);
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