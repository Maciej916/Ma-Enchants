package com.maciej916.maenchants.handler;

import com.maciej916.maenchants.capabilities.IEnchants;
import com.maciej916.maenchants.utils.PlayerUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import static com.maciej916.maenchants.init.ModEnchants.STEP_ASSIST;

public class HandlerStepAssist {

    public static void handlerUpdate(PlayerEntity player, World world) {
        IEnchants enchantsCap = PlayerUtil.getEnchantsCapability(player);
        if (world.isRemote) {
            int lvl = EnchantmentHelper.getMaxEnchantmentLevel(STEP_ASSIST, player);
            if (lvl == 0) {
                if (enchantsCap.getStepAssist()) {
                    player.stepHeight -= 0.4f;
                    enchantsCap.setStepAssist(false);
                }
                return;
            }
            if (!enchantsCap.getStepAssist()) {
                player.stepHeight += 0.4f;
                enchantsCap.setStepAssist(true);
            }
        }
    }

}
