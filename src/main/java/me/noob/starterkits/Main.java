package me.noob.starterkits;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new PlayerSpawnEvent(this.getConfig()), this);
    }
    @Override
    public void onDisable() {
    }
}