package me.noob.simplestarterkits;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleStarterKits extends JavaPlugin {
    @Getter
    private static SimpleStarterKits instance;
    @Getter
    private FileConfiguration settings;

    private void initializeVariables() {
        instance = this;
        instance.saveDefaultConfig();
        settings = this.getSettings();
    }
    @Override
    public void onEnable() {
        initializeVariables();
        getServer().getPluginManager().registerEvents(new PlayerSpawnEvent(), this);
    }
    @Override
    public void onDisable() {
    }
}