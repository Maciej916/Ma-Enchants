package com.maciej916.maenchants.common.capabilities;

import com.maciej916.maenchants.MaEnchants;
import com.maciej916.maenchants.common.capabilities.mod.IModCapability;
import com.maciej916.maenchants.common.capabilities.mod.ModCapabilityProvider;
import com.maciej916.maenchants.common.util.PlayerUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = com.maciej916.maenchants.MaEnchants.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class CapabilitiesAttach {

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(new ResourceLocation(MaEnchants.MODID, "capability"), new ModCapabilityProvider());
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.getOriginal().getCapability(Capabilities.MOD_CAPABILITY, null).isPresent()) {
            IModCapability origEnchantsCap = PlayerUtil.getEnchantsCapability(event.getOriginal());
            IModCapability enchantsCap = PlayerUtil.getEnchantsCapability(event.getPlayer());
            enchantsCap.clone(origEnchantsCap);
        }
    }

}
