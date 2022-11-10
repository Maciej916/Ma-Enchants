package com.maciej916.maenchants.common.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;

public interface IProxy {

    void init();
    Minecraft getClient();
    ClientLevel getClientLevel();
    LocalPlayer getLocalPlayer();

}