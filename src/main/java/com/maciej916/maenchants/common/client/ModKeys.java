package com.maciej916.maenchants.common.client;

import com.maciej916.maenchants.MaEnchants;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class ModKeys {
    private static final String CATEGORY = "Ma Enchants";

    public static final KeyMapping EXCAVATE = new KeyMapping("key." + MaEnchants.MODID + ".excavate", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, CATEGORY);

}