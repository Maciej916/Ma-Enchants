package com.maciej916.maenchants.common.capabilities.basic;

import com.maciej916.maenchants.common.capabilities.mod.IModCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.TagType;
import net.minecraft.nbt.TagVisitor;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.INBTSerializable;

import java.io.DataOutput;
import java.io.IOException;

public class DefaultModCapability implements IModCapability, INBTSerializable<CompoundTag> {

    private byte version;
    private boolean stepAssist;
    private boolean nightVision;
    private int multiJump;
    private boolean multiJumpSpace;
    private boolean excavate;

    public static void register() {
        CapabilityManager.INSTANCE.register(IModCapability.class);
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
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putByte("version", getVersion());
        tag.putBoolean("stepAssist", getStepAssist());
        tag.putBoolean("nightVision", getNightVision());
        tag.putInt("multiJump", getMultiJump());
        tag.putBoolean("multiJumpSpace", getMultiJumpSpace());
        tag.putBoolean("excavate", getExcavateActive());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        setVersion(nbt.getByte("version"));
        setStepAssist(nbt.getBoolean("stepAssist"));
        setNightVision(nbt.getBoolean("nightVision"));
        setMultiJump(nbt.getInt("multiJump"));
        setMultiJumpSpace(nbt.getBoolean("multiJumpSpace"));
        setExcavateActive(nbt.getBoolean("excavate"));
    }

    @Override
    public void write(DataOutput p_129329_) throws IOException {

    }

    @Override
    public byte getId() {
        return 0;
    }

    @Override
    public TagType<?> getType() {
        return null;
    }

    @Override
    public Tag copy() {
        return null;
    }

    @Override
    public void accept(TagVisitor p_178208_) {

    }
}
