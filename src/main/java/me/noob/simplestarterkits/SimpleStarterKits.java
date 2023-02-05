package me.noob.simplestarterkits;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class SimpleStarterKits extends JavaPlugin {

    private static SimpleStarterKits instance;
    private static KitManager kitManager;

    public static void giveKit(Player player) {
        kitManager.giveKit(player);
    }

    public static void saveKit(@NotNull Player player) {
        kitManager.giveKit(player);
    }

    public static void reload() {
        instance.saveDefaultConfig();
        kitManager.reload();
    }

    @Override
    public void onEnable() {
        getLogger().info("Enabling SimpleStarterKits...");
        init();
        getLogger().info("SimpleStarterKits Enabled.");

    }

    private void init() {
        saveDefaultConfig();
        instance = this;
        kitManager = new KitManager(getLogger(), this);
        getCommand("SimpleStarterKits").setExecutor(new SimpleStarterKitsCommand());
        getServer().getPluginManager().registerEvents(new PlayerSpawnEvent(), this);
    }

    @Override
    public void onDisable() {
    }
}