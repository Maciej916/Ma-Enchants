package com.maciej916.maenchants.common.capabilities.player;

import com.maciej916.maenchants.common.registries.ModCapabilities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerCapability implements IPlayerCapability, ICapabilitySerializable<CompoundTag> {

    private final LazyOptional<IPlayerCapability> modCapabilityLazyOptional = LazyOptional.of(() -> this);
    private boolean stepAssist = false;
    private boolean nightVision = false;
    private int multiJump = 0;
    private boolean multiJumpSpace = false;
    private boolean excavate = false;
    private int fasterAttack = 0;
    private BlockPos lastBlockPos = BlockPos.ZERO;

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
    public int getFasterAttack() {
        return fasterAttack;
    }

    @Override
    public void setFasterAttack(int lvl) {
        this.fasterAttack = lvl;
    }

    @Override
    public void clone(IPlayerCapability capability) {
        setStepAssist(capability.getStepAssist());
        setNightVision(capability.getNightVision());
        setMultiJump(capability.getMultiJump());
        setMultiJumpSpace(capability.getMultiJumpSpace());
        setExcavateActive(capability.getExcavateActive());
        setFasterAttack(capability.getFasterAttack());
        setLastBlockPos(capability.getLastBlockPos());
    }

    @Override
    public BlockPos getLastBlockPos() {
        return lastBlockPos;
    }

    @Override
    public void setLastBlockPos(BlockPos blockPos) {
        this.lastBlockPos = blockPos;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("stepAssist", getStepAssist());
        tag.putBoolean("nightVision", getNightVision());
        tag.putInt("multiJump", getMultiJump());
        tag.putBoolean("multiJumpSpace", getMultiJumpSpace());
        tag.putBoolean("excavate", getExcavateActive());
        tag.putInt("fasterAttack", getFasterAttack());
        tag.put("lastBlockPos", NbtUtils.writeBlockPos(getLastBlockPos()));
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        setStepAssist(nbt.getBoolean("stepAssist"));
        setNightVision(nbt.getBoolean("nightVision"));
        setMultiJump(nbt.getInt("multiJump"));
        setMultiJumpSpace(nbt.getBoolean("multiJumpSpace"));
        setExcavateActive(nbt.getBoolean("excavate"));
        setFasterAttack(nbt.getInt("fasterAttack"));
        setLastBlockPos(NbtUtils.readBlockPos(nbt.getCompound("lastBlockPos")));
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ModCapabilities.PLAYER_CAPABILITY) {
            return modCapabilityLazyOptional.cast();
        }
        return LazyOptional.empty();
    }
}
