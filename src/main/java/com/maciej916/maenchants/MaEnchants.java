package com.maciej916.maenchants;

import com.maciej916.maenchants.enchantment.Lumberjack;
import com.maciej916.maenchants.enchantment.Momentum;
import com.maciej916.maenchants.enchantment.ReinforcedTip;
import com.maciej916.maenchants.enchantment.StoneMending;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

@Mod(MaEnchants.MODID)
public class MaEnchants
{
    public static final String MODID = "ma-enchants";

    public MaEnchants() {
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
//        modLoadingContext.registerConfig(ModConfig.Type.CLIENT, ConfigHolder.CLIENT_SPEC);
//        modLoadingContext.registerConfig(ModConfig.Type.SERVER, ConfigHolder.SERVER_SPEC);

        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(Enchantment.class, this::registerEnchantments);
    }


    @SubscribeEvent
    public void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        Set<Enchantment> enchants = new HashSet<>();

        // Tools
        enchants.add(new ReinforcedTip());
        enchants.add(new StoneMending());
        enchants.add(new Lumberjack());
        enchants.add(new Momentum());

        IForgeRegistry<Enchantment> r = event.getRegistry();
        for (Enchantment enchant : enchants) {
            r.register(enchant);
        }
    }

    @ObjectHolder(MODID)
    public static class ObjectHolders {
        public static final Enchantment REINFORCED_TIP = null;
        public static final Enchantment STONE_MENDING = null;
        public static final Enchantment LUMBERJACK = null;
        public static final Enchantment MOMENTUM = null;
    }
}
