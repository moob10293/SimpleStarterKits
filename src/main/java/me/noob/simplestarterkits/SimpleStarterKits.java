package me.noob.simplestarterkits;

import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleStarterKits extends JavaPlugin {
    private static SimpleStarterKits instance;
    private static KitManager kitManager;

    public static void giveKit(Player player) {
        kitManager.give(player);
    }
    @SuppressWarnings("unused")
    public static void setKit(Player player) {
        kitManager.set(player);
    }
    public static void clearKit() {
        kitManager.clear();
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

    @SuppressWarnings("DataFlowIssue")
    private void init() {
        saveDefaultConfig();
        instance = this;
        kitManager = new KitManager(getLogger(), this);
        PluginCommand command = getCommand("SimpleStarterKits");
        command.setExecutor(new SimpleStarterKitsCommand());
        command.setTabCompleter(new TabCompleter());
        getServer().getPluginManager().registerEvents(new PlayerSpawnEvent(), this);
    }

    @Override
    public void onDisable() {
    }
}