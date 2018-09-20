package com.vanillage.validusername;

import com.vanillage.minecraftalphaserver.event.PlayerLoggedInListener;
import com.vanillage.minecraftalphaserver.plugin.Plugin;

public final class ValidUsername extends Plugin {
    @Override
    public void onEnable() {
        getMinecraftServer().getPluginManager().getEventManager().registerListener((PlayerLoggedInListener) new ValidUsernamePlayerLoggedInListener(this));
        getMinecraftServer().log(getName() + " enabled");
    }

    @Override
    public void onDisable() {
        getMinecraftServer().log(getName() + " disabled");
    }
}
