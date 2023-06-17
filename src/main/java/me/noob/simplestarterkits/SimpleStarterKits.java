package me.noob.simplestarterkits;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class SimpleStarterKits extends JavaPlugin {

    private static KitManager kitManager;
    @Getter
    private static SimpleStarterKits instance;
    @Getter
    private static PlayerManager playerManager;
    @Getter
    private static ConfigurationManager configManager;

    public static void giveKit(Player player) {
        kitManager.giveStarterKit(player);
    }

    @SuppressWarnings("unused")
    public static void setKit(@NotNull Player player) {
        kitManager.setStarterKit(player.getInventory());
    }

    public static void clearKit() {
        kitManager.setStarterKit((PlayerInventory) Bukkit.createInventory(null, InventoryType.PLAYER));
    }

    public static void reload() {
        kitManager.reload();
        configManager.reload();
        playerManager.reload();
    }

    @Override
    @SuppressWarnings("DataFlowIssue")
    public void onEnable() {
        instance = this;
        initConfigs();

        getServer().getPluginManager().registerEvents(new PlayerSpawnEvent(), this);

        PluginCommand command = getCommand("SimpleStarterKits");
        command.setExecutor(new SimpleStarterKitsCommand());
        command.setTabCompleter(new TabCompleter());

        getLogger().info("SimpleStarterKits Enabled.");

    }

    private void initConfigs() {
        configManager = new ConfigurationManager(this, "config.yml");
        kitManager = new KitManager(this, "kits.yml");
        playerManager = new PlayerManager(this, "played_before.yml");
    }
}