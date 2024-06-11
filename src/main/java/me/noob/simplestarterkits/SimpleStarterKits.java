package me.noob.simplestarterkits;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public final class SimpleStarterKits extends JavaPlugin {

    private static KitManager kitManager;
    @Getter
    private static Logger staticLogger;
    @Getter
    private static PlayedBeforeManager playedBeforeManager;
    @Getter
    private static ConfigurationManager configManager;

    public static void giveKit(Player player) {
        kitManager.giveStarterKit(player);
    }

    @SuppressWarnings("unused")
//    used by comprehension
    public static void setKit(@NotNull Player player) {
        kitManager.setStarterKit(player.getInventory());
    }

    public static void clearKit() {
        kitManager.setStarterKit((PlayerInventory) Bukkit.createInventory(null, InventoryType.PLAYER));
    }

    public static void reload() {
        kitManager.reload();
        configManager.reload();
        playedBeforeManager.reload();
    }

    @Override
    @SuppressWarnings("DataFlowIssue") // for command.setexecutor
    public void onEnable() {
        staticLogger = this.getLogger();
        initConfigs();

        getServer().getPluginManager().registerEvents(new PlayerSpawnEvent(), this);

        PluginCommand command = getCommand("SimpleStarterKits");
        command.setExecutor(new SimpleStarterKitsCommand());
        command.setTabCompleter(new TabCompleter());

        staticLogger.info("SimpleStarterKits Enabled.");
    }

    private void initConfigs() {
        configManager = new ConfigurationManager("config.yml", this);
        kitManager = new KitManager("kits.yml", this);
        playedBeforeManager = new PlayedBeforeManager("played_before.yml", this);
    }
}