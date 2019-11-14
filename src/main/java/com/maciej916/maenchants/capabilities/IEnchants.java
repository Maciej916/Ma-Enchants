package com.maciej916.maenchants.capabilities;

public interface IEnchants {

    byte getVersion();
    boolean getStepAssist();
    boolean getNightVision();
    int getMultiJump();
    boolean getMultiJumpSpace();

    void setVersion(byte version);
    void setStepAssist(boolean enabled);
    void setNightVision(boolean enabled);
    void setMultiJump(int count);
    void setMultiJumpSpace(boolean enabled);

}
