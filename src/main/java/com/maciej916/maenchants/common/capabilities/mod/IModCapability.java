package com.maciej916.maenchants.common.capabilities.mod;

public interface IModCapability {

    byte getVersion();
    boolean getStepAssist();
    boolean getNightVision();
    int getMultiJump();
    boolean getMultiJumpSpace();
    boolean getExcavateActive();

    void setVersion(byte version);
    void setStepAssist(boolean enabled);
    void setNightVision(boolean enabled);
    void setMultiJump(int count);
    void setMultiJumpSpace(boolean enabled);
    void setExcavateActive(boolean enabled);

    void clone(IModCapability capability);

}
