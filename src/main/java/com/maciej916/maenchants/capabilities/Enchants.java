package com.maciej916.maenchants.capabilities;

public class Enchants implements IEnchants {

    private byte version;
    private boolean stepAssist;
    private boolean nightVision;
    private int multiJump;
    private boolean multiJumpSpace;
    private boolean excavate;

    public Enchants() {
        this.version = 1;
        this.stepAssist = false;
        this.nightVision = false;
        this.multiJump = 0;
        this.multiJumpSpace = false;
        this.excavate = false;
    }

    @Override
    public byte getVersion() {
        return this.version;
    }

    @Override
    public boolean getStepAssist() {
        return this.stepAssist;
    }

    @Override
    public boolean getNightVision() {
        return this.nightVision;
    }

    @Override
    public int getMultiJump() {
        return this.multiJump;
    }

    @Override
    public boolean getMultiJumpSpace() {
        return this.multiJumpSpace;
    }

    @Override
    public boolean getExcavateActive() {
        return excavate;
    }

    @Override
    public void setVersion(byte version) {
        this.version = version;
    }

    @Override
    public void setStepAssist(boolean enabled) {
        this.stepAssist = enabled;
    }

    @Override
    public void setNightVision(boolean enabled) {
        this.nightVision = enabled;
    }

    @Override
    public void setMultiJump(int count) {
        this.multiJump = count;
    }

    @Override
    public void setMultiJumpSpace(boolean enabled) {
        this.multiJumpSpace = enabled;
    }

    @Override
    public void setExcavateActive(boolean enabled) {
        this.excavate = enabled;
    }
}
