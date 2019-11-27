package com.maciej916.maenchants.handler;

import com.maciej916.maenchants.capabilities.IEnchants;
import com.maciej916.maenchants.utils.PlayerUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;

import static com.maciej916.maenchants.init.ModEnchants.STEP_ASSIST;

public class HandlerStepAssist {

    public static void handlerPlayerTick(PlayerEntity player) {
        if (!player.isAlive()) return;

        int lvl = EnchantmentHelper.getMaxEnchantmentLevel(STEP_ASSIST, player);
        IEnchants enchantsCap = PlayerUtil.getEnchantsCapability(player);

        if (lvl == 0) {
            if (enchantsCap.getStepAssist()) {
                player.stepHeight -= 0.4f;
                enchantsCap.setStepAssist(false);
            }
        } else {
            if (!enchantsCap.getStepAssist()) {
                player.stepHeight += 0.4f;
                enchantsCap.setStepAssist(true);
            }
        }
    }

}