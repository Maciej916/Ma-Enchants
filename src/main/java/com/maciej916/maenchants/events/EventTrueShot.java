package com.maciej916.maenchants.events;

import net.minecraft.entity.projectile.AbstractArrowEntity;

import java.util.ArrayList;
import java.util.List;

public class EventTrueShot {

    public static List<AbstractArrowEntity> trueShotArrows = new ArrayList<>();

    public static void event() {
        List<AbstractArrowEntity> removeTrueShot = new ArrayList<>();
        for (AbstractArrowEntity arrow : trueShotArrows) {
            if (arrow.ticksExisted > 600) {
                arrow.remove();
                removeTrueShot.add(arrow);
            }
            if (!arrow.isAlive()) continue;
            arrow.setMotion(arrow.getMotion().scale(1/.99));
            arrow.velocityChanged = true;
        }
        trueShotArrows.removeAll(removeTrueShot);
    }
}
