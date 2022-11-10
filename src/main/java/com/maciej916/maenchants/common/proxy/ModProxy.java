package com.maciej916.maenchants.common.proxy;

import net.minecraftforge.fml.DistExecutor;

public class ModProxy {
    public static final IProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new); // unsafeRunForDist?

    public static void init() {
        PROXY.init();
    }

}
