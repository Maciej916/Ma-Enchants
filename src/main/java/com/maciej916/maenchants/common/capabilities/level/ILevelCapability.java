package com.maciej916.maenchants.common.capabilities.level;

import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public interface ILevelCapability {

    void tick();


    void entityJoinWorldEvent(EntityJoinWorldEvent event);

}
