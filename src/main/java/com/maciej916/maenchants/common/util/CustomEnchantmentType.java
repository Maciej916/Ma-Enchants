package com.maciej916.maenchants.common.util;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.*;

import java.util.function.Predicate;

public class CustomEnchantmentType {

    public static final EnchantmentType PICKAXE = addEnchantment("pickaxe", PickaxeItem.class::isInstance);
    public static final EnchantmentType AXE = addEnchantment("axe", AxeItem.class::isInstance);
    public static final EnchantmentType WEAPON_DIGGER = addEnchantment("weapon_digger", item -> item instanceof SwordItem || item instanceof ToolItem);
    public static final EnchantmentType SHOOTABLE = addEnchantment("shootable", ShootableItem.class::isInstance);

    private static EnchantmentType addEnchantment(String name, Predicate<Item> condition) {
        return EnchantmentType.create(name, condition);
    }
}
