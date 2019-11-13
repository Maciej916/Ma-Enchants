package com.maciej916.maenchants.enchantment;

import com.maciej916.maenchants.MaEnchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.maciej916.maenchants.init.ModEnchants.MOMENTUM;

@Mod.EventBusSubscriber(modid = MaEnchants.MODID)
public class EnchantmentMomentum extends Enchantment {

    public EnchantmentMomentum() {
        super(Rarity.RARE, EnchantmentType.DIGGER, new EquipmentSlotType[]{
                EquipmentSlotType.MAINHAND
        });
    }

    @Override
    public int getMinEnchantability(int level) {
        return 15;
    }

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        PlayerEntity player = event.getPlayer();

        if (EnchantmentHelper.getMaxEnchantmentLevel(MOMENTUM, player) == 0) return;

        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        CompoundNBT compound = stack.getOrCreateTag();
        int momentum = compound.getInt("momentum");
        float oldSpeed = event.getOriginalSpeed();
        float newSpeed = oldSpeed + .05f * momentum;
        event.setNewSpeed(newSpeed);
    }

    @SubscribeEvent
    public static void onBreak(BlockEvent.BreakEvent event) {
        PlayerEntity player = event.getPlayer();

        if (EnchantmentHelper.getMaxEnchantmentLevel(MOMENTUM, player) == 0) return;

        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        CompoundNBT compound = stack.getOrCreateTag();
        int momentum = compound.getInt("momentum");
        String cachedBlock = compound.getString("block");
        String currentBlock = event.getState().getBlock().toString();
        if (!cachedBlock.equals(currentBlock)) {
            compound.putInt("momentum", 0);
            compound.putString("block", currentBlock);
        } else {
            compound.putInt("momentum", momentum + 1);
        }
    }

}