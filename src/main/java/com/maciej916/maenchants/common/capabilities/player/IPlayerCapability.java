package com.maciej916.maenchants.common.capabilities.player;

public interface IPlayerCapability {

    boolean getStepAssist();
    boolean getNightVision();
    int getMultiJump();
    boolean getMultiJumpSpace();
    boolean getExcavateActive();

    void setStepAssist(boolean enabled);
    void setNightVision(boolean enabled);
    void setMultiJump(int count);
    void setMultiJumpSpace(boolean enabled);
    void setExcavateActive(boolean enabled);

    void clone(IPlayerCapability capability);

}
