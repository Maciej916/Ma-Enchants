package com.maciej916.maenchants.init;

import com.maciej916.maenchants.MaEnchants;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import javax.annotation.Nonnull;
import java.util.function.Supplier;


public final class ModItemGroups {

	public static final ItemGroup MOD_ITEM_GROUP = new ModItemGroup(MaEnchants.MODID, () -> new ItemStack(Items.ENCHANTED_BOOK));

	public static final class ModItemGroup extends ItemGroup {

		@Nonnull
		private final Supplier<ItemStack> iconSupplier;

		public ModItemGroup(@Nonnull final String name, @Nonnull final Supplier<ItemStack> iconSupplier) {
			super(name);
			this.iconSupplier = iconSupplier;
		}

		@Override
		@Nonnull
		public ItemStack createIcon() {
			return iconSupplier.get();
		}

	}
}