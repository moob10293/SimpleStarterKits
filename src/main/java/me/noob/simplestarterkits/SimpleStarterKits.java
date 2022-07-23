package me.noob.simplestarterkits;

import org.bukkit.plugin.java.JavaPlugin;

public class SimpleStarterKits extends JavaPlugin {
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new PlayerSpawnEvent(this.getConfig()), this);
    }
    @Override
    public void onDisable() {
    }
}