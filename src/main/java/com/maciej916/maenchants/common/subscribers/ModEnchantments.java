package com.maciej916.maenchants.common.subscribers;

import com.maciej916.maenchants.common.curse.CurseAquaphobia;
import com.maciej916.maenchants.common.curse.CurseBreaking;
import com.maciej916.maenchants.common.curse.CurseButterfingers;
import com.maciej916.maenchants.common.curse.CurseDeath;
import com.maciej916.maenchants.common.enchantment.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModEnchantments {

    public static Enchantment curse_breaking;
    public static Enchantment curse_butterfingers;
    public static Enchantment curse_aquaphobia;
    public static Enchantment curse_death;

    public static Enchantment reinforced_tip;
    public static Enchantment stone_mending;
    public static Enchantment lumberjack;
    public static Enchantment momentum;
    public static Enchantment butchering;

    public static Enchantment true_shot;
    public static Enchantment quick_draw;
    public static Enchantment floating;
    public static Enchantment paralysis;
    public static Enchantment detonation;

    public static Enchantment combo;
    public static Enchantment faster_attack;
    public static Enchantment lifesteal;
    public static Enchantment ice_aspect;
    public static Enchantment wisdom;

    public static Enchantment blazing_walker;
    public static Enchantment step_assist;
    public static Enchantment night_vision;
    public static Enchantment multi_jump;
    public static Enchantment soft_fall;

    public static Enchantment timeless;

    @SubscribeEvent
    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) {

        // Curse
        curse_breaking = registerEnchantment(new CurseBreaking(), "curse_breaking");
        curse_butterfingers = registerEnchantment(new CurseButterfingers(), "curse_butterfingers");
        curse_aquaphobia = registerEnchantment(new CurseAquaphobia(), "curse_aquaphobia");
        curse_death = registerEnchantment(new CurseDeath(), "curse_death");

        // Tools
        reinforced_tip = registerEnchantment(new EnchantmentReinforcedTip(), "reinforced_tip");
        stone_mending = registerEnchantment(new EnchantmentStoneMending(), "stone_mending");
        lumberjack = registerEnchantment(new EnchantmentLumberjack(), "lumberjack");
        momentum = registerEnchantment(new EnchantmentMomentum(), "momentum");
        butchering = registerEnchantment(new EnchantmentButchering(), "butchering");

        // Bows
        true_shot = registerEnchantment(new EnchantmentTrueShot(), "true_shot");
        quick_draw = registerEnchantment(new EnchantmentQuickDraw(), "quick_draw");
        floating = registerEnchantment(new EnchantmentFloating(), "floating");
        paralysis = registerEnchantment(new EnchantmentParalysis(), "paralysis");
        detonation = registerEnchantment(new EnchantmentDetonation(), "detonation");

        // Swords
        combo = registerEnchantment(new EnchantmentCombo(), "combo");
        faster_attack = registerEnchantment(new EnchantmentFasterAttack(), "faster_attack");
        lifesteal = registerEnchantment(new EnchantmentLifesteal(), "lifesteal");
        ice_aspect = registerEnchantment(new EnchantmentIceAspect(), "ice_aspect");
        wisdom = registerEnchantment(new EnchantmentWisdom(), "wisdom");

        // Armour
        blazing_walker = registerEnchantment(new EnchantmentBlazingWalker(), "blazing_walker");
        step_assist = registerEnchantment(new EnchantmentStepAssist(), "step_assist");
        night_vision = registerEnchantment(new EnchantmentNightVision(), "night_vision");
        multi_jump = registerEnchantment(new EnchantmentMultiJump(), "multi_jump");
        soft_fall = registerEnchantment(new EnchantmentSoftFall(), "soft_fall");

        // Other
        timeless = registerEnchantment(new EnchantmentTimeless(), "timeless");

    }

    public static Enchantment registerEnchantment(Enchantment enchantment, String name) {
        enchantment.setRegistryName(name);
        ForgeRegistries.ENCHANTMENTS.register(enchantment);
        return enchantment;
    }
}
