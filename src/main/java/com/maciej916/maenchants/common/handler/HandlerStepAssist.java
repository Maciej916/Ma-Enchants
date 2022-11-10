package com.maciej916.maenchants.common.handler;

import com.maciej916.maenchants.common.capabilities.player.IPlayerCapability;
import com.maciej916.maenchants.common.registries.ModEnchantments;
import com.maciej916.maenchants.common.util.PlayerUtil;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;

import java.util.Objects;

public class HandlerStepAssist {

    public static void handlerPlayerTick(Player player) {
        IPlayerCapability enchantsCap = PlayerUtil.getAliveEnchantsCapability(player);
        if (enchantsCap == null) return;

        ItemStack stack = player.getItemBySlot(EquipmentSlot.FEET);

        int lvl = stack.getEnchantmentLevel(ModEnchantments.STEP_ASSIST.get());
        if (lvl == 0) {
            if (enchantsCap.getStepAssist()) {
                double stepHeight = Objects.requireNonNull(player.getAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get())).getValue();
                Objects.requireNonNull(player.getAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get())).setBaseValue(stepHeight - 0.4f);
                enchantsCap.setStepAssist(false);
            }
        } else {
            if (!enchantsCap.getStepAssist()) {
                double stepHeight = Objects.requireNonNull(player.getAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get())).getValue();
                Objects.requireNonNull(player.getAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get())).setBaseValue(stepHeight + 0.4f);
                enchantsCap.setStepAssist(true);
            }
        }
    }

}