package com.maciej916.maenchants.common.capabilities.player;

import net.minecraft.core.BlockPos;

public interface IPlayerCapability {

    boolean getStepAssist();
    void setStepAssist(boolean enabled);

    boolean getNightVision();
    int getMultiJump();
    boolean getMultiJumpSpace();
    boolean getExcavateActive();

    void setNightVision(boolean enabled);
    void setMultiJump(int count);
    void setMultiJumpSpace(boolean enabled);
    void setExcavateActive(boolean enabled);

    int getFasterAttack();
    void setFasterAttack(int lvl);

    void clone(IPlayerCapability capability);

    BlockPos getLastBlockPos();
    void setLastBlockPos(BlockPos blockPos);
}
