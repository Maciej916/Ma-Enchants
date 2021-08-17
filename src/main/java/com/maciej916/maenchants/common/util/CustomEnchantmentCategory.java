package com.maciej916.maenchants.common.util;

import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import java.util.function.Predicate;

public class CustomEnchantmentCategory {

    public static final EnchantmentCategory PICKAXE = addEnchantment("pickaxe", PickaxeItem.class::isInstance);
    public static final EnchantmentCategory AXE = addEnchantment("axe", AxeItem.class::isInstance);
    public static final EnchantmentCategory WEAPON_DIGGER = addEnchantment("weapon_digger", item ->
            item instanceof SwordItem ||
            item instanceof DiggerItem
    );
    public static final EnchantmentCategory SHOOTABLE = addEnchantment("shootable", ProjectileWeaponItem.class::isInstance);

    private static EnchantmentCategory addEnchantment(String name, Predicate<Item> condition) {
        return EnchantmentCategory.create(name, condition);
    }
}
