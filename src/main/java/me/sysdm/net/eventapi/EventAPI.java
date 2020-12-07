package me.sysdm.net.eventapi;

import org.bukkit.plugin.java.JavaPlugin;

public final class EventAPI extends JavaPlugin {

    private static EventAPI instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled!");
    }

    public static EventAPI getInstance() {
        return instance;
    }
}
