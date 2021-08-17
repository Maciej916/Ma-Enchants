package com.maciej916.maenchants.common.proxy;

import com.maciej916.maenchants.client.Keys;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

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
    public Level getClientWorld() {
        return Minecraft.getInstance().player.getCommandSenderWorld();
    }

    @Override
    public Player getClientPlayer() {
        return Minecraft.getInstance().player;
    }

}
