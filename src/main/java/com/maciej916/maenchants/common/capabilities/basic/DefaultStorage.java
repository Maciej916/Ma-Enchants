package com.maciej916.maenchants.common.capabilities.basic;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;

public class DefaultStorage<T> implements Capability.IStorage<T> {

    @Override
    public INBT writeNBT(Capability<T> capability, T instance, Direction side) {
        if (instance instanceof INBTSerializable) {
            return ((INBTSerializable<?>) instance).serializeNBT();
        }
        return new CompoundNBT();
    }

    @Override
    public void readNBT(Capability<T> capability, T instance, Direction side, INBT nbt) {
        if (instance instanceof INBTSerializable) {
            Class<? extends INBT> nbtClass = ((INBTSerializable<?>) instance).serializeNBT().getClass();
            if (nbtClass.isInstance(nbt)) {
                ((INBTSerializable) instance).deserializeNBT(nbtClass.cast(nbt));
            }
        }
    }

}

