package com.maciej916.maenchants.capabilities;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class EnchantsProvider implements ICapabilitySerializable<INBT> {

    @CapabilityInject(IEnchants.class)
    public static final Capability<IEnchants> ENCHANTS_CAPABILITY = null;

    private LazyOptional<IEnchants> instance = LazyOptional.of(ENCHANTS_CAPABILITY::getDefaultInstance);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == ENCHANTS_CAPABILITY ? instance.cast() : LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT() {
        // @formatter:off
        return ENCHANTS_CAPABILITY.getStorage().writeNBT(ENCHANTS_CAPABILITY, this.instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null);
        // @formatter:on
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        // @formatter:off
        ENCHANTS_CAPABILITY.getStorage().readNBT(ENCHANTS_CAPABILITY, this.instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null, nbt);
        // @formatter:on
    }

}