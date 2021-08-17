package com.maciej916.maenchants.common.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public interface IProxy {

    void init();
    Minecraft getClient();
    Level getClientWorld();
    Player getClientPlayer();

}