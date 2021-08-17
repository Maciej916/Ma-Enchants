package com.maciej916.maenchants.common.handler;

import com.maciej916.maenchants.common.capabilities.mod.IModCapability;
import com.maciej916.maenchants.common.util.PlayerUtil;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import static com.maciej916.maenchants.common.registries.ModEnchants.STEP_ASSIST;

public class HandlerStepAssist {

    public static void handlerPlayerTick(Player player) {
        IModCapability enchantsCap = PlayerUtil.getAliveEnchantsCapability(player);
        if (enchantsCap == null) return;

        ItemStack stack = player.getItemBySlot(EquipmentSlot.FEET);
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(STEP_ASSIST, stack);

        if (lvl == 0) {
            if (enchantsCap.getStepAssist()) {
                player.maxUpStep -= 0.4f;
                enchantsCap.setStepAssist(false);
            }
        } else {
            if (!enchantsCap.getStepAssist()) {
                player.maxUpStep += 0.4f;
                enchantsCap.setStepAssist(true);
            }
        }
    }

}