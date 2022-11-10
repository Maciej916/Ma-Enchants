package com.maciej916.maenchants.common.capabilities.level;

import net.minecraftforge.event.entity.EntityJoinLevelEvent;

public interface ILevelCapability {

    void tick();
    void entityJoinLevelEvent(EntityJoinLevelEvent event);

}
