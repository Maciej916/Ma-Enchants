package com.maciej916.maenchants.enchantment;

import com.maciej916.maenchants.MaEnchants;
import com.maciej916.maenchants.network.Networking;
import com.maciej916.maenchants.network.PacketCombo;
import com.maciej916.maenchants.utils.CustomEnchantmentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.maciej916.maenchants.utils.Enchants.COMBO;

@Mod.EventBusSubscriber(modid = MaEnchants.MODID)
public class EnchantmentCombo extends Enchantment {
    public EnchantmentCombo(String name) {
        super(Rarity.RARE, CustomEnchantmentType.SWORD, new EquipmentSlotType[]{
                EquipmentSlotType.MAINHAND
        });
        this.setRegistryName(name);
    }

    @Override
    public int getMinEnchantability(int level) {
        return 15;
    }

    private static boolean handled = false;

    @Override
    public void onEntityDamaged(LivingEntity entity, Entity target, int level) {
        if (handled) {
            handled = false;
            return;
        }

        if (!(entity instanceof PlayerEntity)) return;
        PlayerEntity player = (PlayerEntity) entity;
        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        CompoundNBT compound = stack.getOrCreateTag();
        target.attackEntityFrom(DamageSource.causePlayerDamage(player), compound.getInt("combo") * .5f);
        compound.putInt("combo", compound.getInt("combo") + 1);

        handled = true;
    }

    @SubscribeEvent
    public static void onMiss(PlayerInteractEvent.LeftClickEmpty event) {
        if (EnchantmentHelper.getEnchantmentLevel(COMBO, event.getItemStack()) == 0) return;
        Networking.INSTANCE.sendToServer(new PacketCombo());
    }

    @SubscribeEvent
    public static void onHitBlock(PlayerInteractEvent.LeftClickBlock event) {
        if (EnchantmentHelper.getEnchantmentLevel(COMBO, event.getItemStack()) == 0) return;
        Networking.INSTANCE.sendToServer(new PacketCombo());
    }
}