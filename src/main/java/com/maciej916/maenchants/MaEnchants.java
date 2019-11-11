package com.maciej916.maenchants;

import com.maciej916.maenchants.enchantment.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.HashSet;
import java.util.Set;

@Mod(MaEnchants.MODID)
public class MaEnchants {
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

        // Bows
        enchants.add(new TrueShot());
        enchants.add(new QuickDraw());
        enchants.add(new Floating());
        enchants.add(new Paralysis());


        IForgeRegistry<Enchantment> r = event.getRegistry();
        for (Enchantment enchant : enchants) {
            r.register(enchant);
        }
    }

    @ObjectHolder(MODID)
    public static class ObjectHolders {
        // Tools
        public static final Enchantment REINFORCED_TIP = null;
        public static final Enchantment STONE_MENDING = null;
        public static final Enchantment LUMBERJACK = null;
        public static final Enchantment MOMENTUM = null;

        // Bows
        public static final Enchantment TRUE_SHOT = null;
        public static final Enchantment QUICK_DRAW = null;
        public static final Enchantment FLOATING = null;
        public static final Enchantment PARALYSIS  = null;

    }
}
