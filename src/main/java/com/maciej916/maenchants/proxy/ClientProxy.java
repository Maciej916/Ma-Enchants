package com.maciej916.maenchants.proxy;

import com.maciej916.maenchants.client.Keys;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy {

    @Override
    public void init() {
        Keys.registerKeys();
    }

    @Override
    public Minecraft getClient() {
        return Minecraft.getInstance();
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }

    @Override
    public PlayerEntity getClientPlayer() {
        return Minecraft.getInstance().player;
    }

}
