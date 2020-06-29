package com.maciej916.maenchants.common.capabilities.mod;

import com.maciej916.maenchants.common.capabilities.basic.DefaultModCapability;
import com.maciej916.maenchants.common.capabilities.mod.IModCapability;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import static com.maciej916.maenchants.common.capabilities.Capabilities.MOD_CAPABILITY;

public class ModCapabilityProvider implements ICapabilitySerializable<INBT> {

    private final LazyOptional<IModCapability> instance = LazyOptional.of(DefaultModCapability::new);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == MOD_CAPABILITY ? instance.cast() : LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT() {
        return MOD_CAPABILITY.getStorage().writeNBT(MOD_CAPABILITY, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null);
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        MOD_CAPABILITY.getStorage().readNBT(MOD_CAPABILITY, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null, nbt);
    }

}