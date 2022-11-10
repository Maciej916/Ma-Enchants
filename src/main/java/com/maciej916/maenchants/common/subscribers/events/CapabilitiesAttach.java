package com.maciej916.maenchants.common.subscribers.events;

import com.maciej916.maenchants.MaEnchants;
import com.maciej916.maenchants.common.capabilities.level.ILevelCapability;
import com.maciej916.maenchants.common.capabilities.level.LevelCapability;
import com.maciej916.maenchants.common.capabilities.player.IPlayerCapability;
import com.maciej916.maenchants.common.capabilities.player.PlayerCapability;
import com.maciej916.maenchants.common.registries.ModCapabilities;
import com.maciej916.maenchants.common.util.PlayerUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = com.maciej916.maenchants.MaEnchants.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class CapabilitiesAttach {

    @SubscribeEvent
    public static void onAttachLevelCapabilities(AttachCapabilitiesEvent<Level> event) {
        Level level = event.getObject();
        if (!level.isClientSide()) {
            event.addCapability(new ResourceLocation(MaEnchants.MODID, "level"), new LevelCapability(level));
        }
    }

    @SubscribeEvent
    public static void onWorldTick(TickEvent.LevelTickEvent event) {
        if (!event.level.isClientSide()) {
            event.level.getCapability(ModCapabilities.LEVEL_CAPABILITY).ifPresent(ILevelCapability::tick);
        }
    }

    @SubscribeEvent
    public static void onAttachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(new ResourceLocation(MaEnchants.MODID, "player"), new PlayerCapability());
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.getOriginal().getCapability(ModCapabilities.PLAYER_CAPABILITY, null).isPresent()) {
            IPlayerCapability origEnchantsCap = PlayerUtil.getEnchantsCapability(event.getOriginal());
            IPlayerCapability enchantsCap = PlayerUtil.getEnchantsCapability(event.getEntity());
            enchantsCap.clone(origEnchantsCap);
        }
    }

}
