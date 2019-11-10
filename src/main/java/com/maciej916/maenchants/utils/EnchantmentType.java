package com.maciej916.maenchants.utils;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;

import java.util.function.Predicate;

public class EnchantmentType {

    public static final net.minecraft.enchantment.EnchantmentType PICKAXE = addEnchantment("pickaxe", PickaxeItem.class::isInstance);
    public static final net.minecraft.enchantment.EnchantmentType AXE = addEnchantment("axe", AxeItem.class::isInstance);

    private static net.minecraft.enchantment.EnchantmentType addEnchantment(String name, Predicate<Item> condition) {
        return net.minecraft.enchantment.EnchantmentType.create(name, condition);
    }
}
