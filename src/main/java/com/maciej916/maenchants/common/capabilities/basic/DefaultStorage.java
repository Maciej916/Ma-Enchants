package com.maciej916.maenchants.common.capabilities.basic;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DefaultStorage<T extends Tag> implements ICapabilitySerializable<T> {

//    @Override
//    public Tag writeNBT(Capability<T> capability, T instance, Direction side) {
//        if (instance instanceof INBTSerializable) {
//            return ((INBTSerializable<?>) instance).serializeNBT();
//        }
//        return new CompoundTag();
//    }
//
//    @Override
//    public void readNBT(Capability<T> capability, T instance, Direction side, Tag nbt) {
//        if (instance instanceof INBTSerializable) {
//            Class<? extends Tag> nbtClass = ((INBTSerializable<?>) instance).serializeNBT().getClass();
//            if (nbtClass.isInstance(nbt)) {
//                ((INBTSerializable) instance).deserializeNBT(nbtClass.cast(nbt));
//            }
//        }
//    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return null;
    }

    @Override
    public T serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(T nbt) {

    }
}

