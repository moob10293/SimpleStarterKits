package me.noob.simplestarterkits;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class SimpleStarterKits extends JavaPlugin {
    @Getter
    private static SimpleStarterKits instance;
    @Getter
    private FileConfiguration config;

    private void initializeVariables() {
        instance = this;
        instance.saveDefaultConfig();
        config = this.getConfig();
    }
    @Override
    public void onEnable() {
        initializeVariables();
        getServer().getPluginManager().registerEvents(new PlayerSpawnEvent(config), this);
    }
    @Override
    public void onDisable() {
    }
}