package com.maciej916.maenchants.common.registries;

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

    public static Enchantment CURSE_BREAKING;
    public static Enchantment CURSE_BUTTERFINGERS;
    public static Enchantment CURSE_AQUAPHOBIA;
    public static Enchantment CURSE_DEATH;

    public static Enchantment REINFORCED_TIP;
    public static Enchantment STONE_MENDING;
    public static Enchantment LUMBERJACK;
    public static Enchantment MOMENTUM;
    public static Enchantment BUTCHERING;

    public static Enchantment TRUE_SHOT;
    public static Enchantment QUICK_DRAW;
    public static Enchantment FLOATING;
    public static Enchantment PARALYSIS;
    public static Enchantment DETONATION;

    public static Enchantment COMBO;
    public static Enchantment FASTER_ATTACK;
    public static Enchantment LIFESTEAL;
    public static Enchantment ICE_ASPECT;
    public static Enchantment WISDOM;

    public static Enchantment BLAZING_WALKER;
    public static Enchantment STEP_ASSIST;
    public static Enchantment NIGHT_VISION;
    public static Enchantment MULTI_JUMP;
    public static Enchantment SOFT_FALL;

    public static Enchantment TIMELESS;

    @SubscribeEvent
    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) {

        // Curse
        CURSE_BREAKING = registerEnchantment(new CurseBreaking(), "curse_breaking");
        CURSE_BUTTERFINGERS = registerEnchantment(new CurseButterfingers(), "curse_butterfingers");
        CURSE_AQUAPHOBIA = registerEnchantment(new CurseAquaphobia(), "curse_aquaphobia");
        CURSE_DEATH = registerEnchantment(new CurseDeath(), "curse_death");

        // Tools
        REINFORCED_TIP = registerEnchantment(new EnchantmentReinforcedTip(), "reinforced_tip");
        STONE_MENDING = registerEnchantment(new EnchantmentStoneMending(), "stone_mending");
        LUMBERJACK = registerEnchantment(new EnchantmentLumberjack(), "lumberjack");
        MOMENTUM = registerEnchantment(new EnchantmentMomentum(), "momentum");
        BUTCHERING = registerEnchantment(new EnchantmentButchering(), "butchering");

        // Bows
        TRUE_SHOT = registerEnchantment(new EnchantmentTrueShot(), "true_shot");
        QUICK_DRAW = registerEnchantment(new EnchantmentQuickDraw(), "quick_draw");
        FLOATING = registerEnchantment(new EnchantmentFloating(), "floating");
        PARALYSIS = registerEnchantment(new EnchantmentParalysis(), "paralysis");
        DETONATION = registerEnchantment(new EnchantmentDetonation(), "detonation");

        // Swords
        COMBO = registerEnchantment(new EnchantmentCombo(), "combo");
        FASTER_ATTACK = registerEnchantment(new EnchantmentFasterAttack(), "faster_attack");
        LIFESTEAL = registerEnchantment(new EnchantmentLifesteal(), "lifesteal");
        ICE_ASPECT = registerEnchantment(new EnchantmentIceAspect(), "ice_aspect");
        WISDOM = registerEnchantment(new EnchantmentWisdom(), "wisdom");

        // Armour
        BLAZING_WALKER = registerEnchantment(new EnchantmentBlazingWalker(), "blazing_walker");
        STEP_ASSIST = registerEnchantment(new EnchantmentStepAssist(), "step_assist");
        NIGHT_VISION = registerEnchantment(new EnchantmentNightVision(), "night_vision");
        MULTI_JUMP = registerEnchantment(new EnchantmentMultiJump(), "multi_jump");
        SOFT_FALL = registerEnchantment(new EnchantmentSoftFall(), "soft_fall");

        // Other
        TIMELESS = registerEnchantment(new EnchantmentTimeless(), "timeless");

    }

    public static Enchantment registerEnchantment(Enchantment enchantment, String name) {
        enchantment.setRegistryName(name);
        ForgeRegistries.ENCHANTMENTS.register(enchantment);
        return enchantment;
    }
}
