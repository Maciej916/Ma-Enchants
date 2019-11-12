package com.maciej916.maenchants.utils;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.*;

import java.util.function.Predicate;

public class CustomEnchantmentType {

    public static final net.minecraft.enchantment.EnchantmentType PICKAXE = addEnchantment("pickaxe", PickaxeItem.class::isInstance);
    public static final net.minecraft.enchantment.EnchantmentType AXE = addEnchantment("axe", AxeItem.class::isInstance);
    public static final EnchantmentType SWORD = addEnchantment("weapons", item -> item instanceof SwordItem);

    private static net.minecraft.enchantment.EnchantmentType addEnchantment(String name, Predicate<Item> condition) {
        return net.minecraft.enchantment.EnchantmentType.create(name, condition);
    }
}
