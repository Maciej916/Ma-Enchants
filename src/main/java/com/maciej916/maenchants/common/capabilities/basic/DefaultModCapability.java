package com.maciej916.maenchants.common.capabilities.basic;

import com.maciej916.maenchants.common.capabilities.mod.IModCapability;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.INBTSerializable;

public class DefaultModCapability implements IModCapability, INBTSerializable<CompoundNBT> {

    private byte version;
    private boolean stepAssist;
    private boolean nightVision;
    private int multiJump;
    private boolean multiJumpSpace;
    private boolean excavate;

    public static void register() {
        CapabilityManager.INSTANCE.register(IModCapability.class, new DefaultStorage<>(), DefaultModCapability::new);
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

    @Override
    public void clone(IModCapability capability) {
        setVersion(capability.getVersion());
        setStepAssist(capability.getStepAssist());
        setNightVision(capability.getNightVision());
        setMultiJump(capability.getMultiJump());
        setMultiJumpSpace(capability.getMultiJumpSpace());
        setExcavateActive(capability.getExcavateActive());
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT tag = new CompoundNBT();
        tag.putByte("version", getVersion());
        tag.putBoolean("stepAssist", getStepAssist());
        tag.putBoolean("nightVision", getNightVision());
        tag.putInt("multiJump", getMultiJump());
        tag.putBoolean("multiJumpSpace", getMultiJumpSpace());
        tag.putBoolean("excavate", getExcavateActive());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        setVersion(nbt.getByte("version"));
        setStepAssist(nbt.getBoolean("stepAssist"));
        setNightVision(nbt.getBoolean("nightVision"));
        setMultiJump(nbt.getInt("multiJump"));
        setMultiJumpSpace(nbt.getBoolean("multiJumpSpace"));
        setExcavateActive(nbt.getBoolean("excavate"));
    }
}
