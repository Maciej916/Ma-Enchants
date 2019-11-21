package com.maciej916.maenchants.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class EnchantsStorage implements Capability.IStorage<IEnchants> {

    @Override
    public INBT writeNBT(Capability<IEnchants> capability, IEnchants instance, Direction side) {
        CompoundNBT tag = new CompoundNBT();
        tag.putByte("version", instance.getVersion());
        tag.putBoolean("stepAssist", instance.getStepAssist());
        tag.putBoolean("nightVision", instance.getNightVision());
        tag.putInt("multiJump", instance.getMultiJump());
        tag.putBoolean("excavate", instance.getExcavateActive());
        return tag;
    }

    @Override
    public void readNBT(Capability<IEnchants> capability, IEnchants instance, Direction side, INBT nbt) {
        CompoundNBT tag = (CompoundNBT) nbt;
        instance.setVersion(tag.getByte("version"));
        instance.setStepAssist(tag.getBoolean("stepAssist"));
        instance.setNightVision(tag.getBoolean("nightVision"));
        instance.setMultiJump(tag.getInt("multiJump"));
        instance.setExcavateActive(tag.getBoolean("excavate"));
    }

}
