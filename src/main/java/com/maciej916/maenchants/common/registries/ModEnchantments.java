package com.maciej916.maenchants.common.registries;

import com.google.common.base.Supplier;
import com.maciej916.maenchants.common.curse.CurseAquaphobia;
import com.maciej916.maenchants.common.curse.CurseBreaking;
import com.maciej916.maenchants.common.curse.CurseButterfingers;
import com.maciej916.maenchants.common.curse.CurseDeath;
import com.maciej916.maenchants.common.enchantment.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.maciej916.maenchants.MaEnchants.MODID;

public final class ModEnchantments {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MODID);

    public static final RegistryObject<Enchantment> CURSE_BREAKING = registerEnchantment("curse_breaking", CurseBreaking::new);
    public static final RegistryObject<Enchantment> CURSE_BUTTERFINGERS = registerEnchantment("curse_butterfingers", CurseButterfingers::new);
    public static final RegistryObject<Enchantment> CURSE_AQUAPHOBIA = registerEnchantment("curse_aquaphobia", CurseAquaphobia::new);
    public static final RegistryObject<Enchantment> CURSE_DEATH = registerEnchantment("curse_death", CurseDeath::new);

    public static final RegistryObject<Enchantment> REINFORCED_TIP = registerEnchantment("reinforced_tip", EnchantmentReinforcedTip::new);
    public static final RegistryObject<Enchantment> STONE_MENDING = registerEnchantment("stone_mending", EnchantmentStoneMending::new);
    public static final RegistryObject<Enchantment> LUMBERJACK = registerEnchantment("lumberjack", EnchantmentLumberjack::new);
    public static final RegistryObject<Enchantment> MOMENTUM = registerEnchantment("momentum", EnchantmentMomentum::new);
    public static final RegistryObject<Enchantment> BUTCHERING = registerEnchantment("butchering", EnchantmentButchering::new);

    public static final RegistryObject<Enchantment> TRUE_SHOT = registerEnchantment("true_shot", EnchantmentTrueShot::new);
    public static final RegistryObject<Enchantment> QUICK_DRAW = registerEnchantment("quick_draw", EnchantmentQuickDraw::new);
    public static final RegistryObject<Enchantment> FLOATING = registerEnchantment("floating", EnchantmentFloating::new);
    public static final RegistryObject<Enchantment> PARALYSIS = registerEnchantment("paralysis", EnchantmentParalysis::new);
    public static final RegistryObject<Enchantment> DETONATION = registerEnchantment("detonation", EnchantmentDetonation::new);

    public static final RegistryObject<Enchantment> COMBO = registerEnchantment("combo", EnchantmentCombo::new);
    public static final RegistryObject<Enchantment> FASTER_ATTACK = registerEnchantment("faster_attack", EnchantmentFasterAttack::new);
    public static final RegistryObject<Enchantment> LIFESTEAL = registerEnchantment("lifesteal", EnchantmentLifesteal::new);
    public static final RegistryObject<Enchantment> ICE_ASPECT = registerEnchantment("ice_aspect", EnchantmentIceAspect::new);
    public static final RegistryObject<Enchantment> WISDOM = registerEnchantment("wisdom", EnchantmentWisdom::new);

    public static final RegistryObject<Enchantment> BLAZING_WALKER = registerEnchantment("blazing_walker", EnchantmentBlazingWalker::new);
    public static final RegistryObject<Enchantment> STEP_ASSIST = registerEnchantment("step_assist", EnchantmentStepAssist::new);
    public static final RegistryObject<Enchantment> NIGHT_VISION = registerEnchantment("night_vision", EnchantmentNightVision::new);
    public static final RegistryObject<Enchantment> MULTI_JUMP = registerEnchantment("multi_jump", EnchantmentMultiJump::new);
    public static final RegistryObject<Enchantment> SOFT_FALL = registerEnchantment("soft_fall", EnchantmentSoftFall::new);

    public static final RegistryObject<Enchantment> TIMELESS = registerEnchantment("timeless", EnchantmentTimeless::new);

    private static <T extends Enchantment> RegistryObject<T> registerEnchantment(String name, Supplier<T> block) {
        return ENCHANTMENTS.register(name, block);
    }

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }

}
