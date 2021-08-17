package com.maciej916.maenchants.common.capabilities.mod;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ModCapabilityStorage implements ICapabilitySerializable<IModCapability> {
//
//    @Override
//    public Tag writeNBT(Capability<IModCapability> capability, IModCapability instance, Direction side) {
//        CompoundTag tag = new CompoundTag();
//        tag.putByte("version", instance.getVersion());
//        tag.putBoolean("stepAssist", instance.getStepAssist());
//        tag.putBoolean("nightVision", instance.getNightVision());
//        tag.putInt("multiJump", instance.getMultiJump());
//        tag.putBoolean("excavate", instance.getExcavateActive());
//        return tag;
//    }
//
//    @Override
//    public void readNBT(Capability<IModCapability> capability, IModCapability instance, Direction side, Tag nbt) {
//        CompoundTag tag = (CompoundTag) nbt;
//        instance.setVersion(tag.getByte("version"));
//        instance.setStepAssist(tag.getBoolean("stepAssist"));
//        instance.setNightVision(tag.getBoolean("nightVision"));
//        instance.setMultiJump(tag.getInt("multiJump"));
//        instance.setExcavateActive(tag.getBoolean("excavate"));
//    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return null;
    }

    @Override
    public IModCapability serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(IModCapability nbt) {

    }
}
