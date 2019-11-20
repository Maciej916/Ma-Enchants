package com.maciej916.maenchants.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ServerProxy implements IProxy {

    @Override
    public void init() {

    }

    @Override
    public Minecraft getClient() {
        throw new IllegalStateException("Only run this on the client!");
    }

    @Override
    public World getClientWorld() {
        throw new IllegalStateException("Only run this on the client!");
    }

    @Override
    public PlayerEntity getClientPlayer() {
        throw new IllegalStateException("Only run this on the client!");
    }

}