package me.noob.simplestarterkits;

import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleStarterKits extends JavaPlugin {
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
        kitManager.reload();
    }

    @Override
    @SuppressWarnings("DataFlowIssue")
    public void onEnable() {
        saveDefaultConfig();
        saveResource("kits.yml", false);

        kitManager = new KitManager(getLogger(), this);

        getServer().getPluginManager().registerEvents(new PlayerSpawnEvent(), this);

        PluginCommand command = getCommand("SimpleStarterKits");
        command.setExecutor(new SimpleStarterKitsCommand());
        command.setTabCompleter(new TabCompleter());

        getLogger().info("SimpleStarterKits Enabled.");

    }
}