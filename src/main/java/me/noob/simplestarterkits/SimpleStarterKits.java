package me.noob.simplestarterkits;

import lombok.SneakyThrows;
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

    public static void reload(){
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
        instance=this;
        saveDefaultConfig();
        initCommands();
        initEvents();
        kitManager = new KitManager(getLogger(), this);
    }

    private void initEvents() {
        getServer().getPluginManager().registerEvents(new PlayerSpawnEvent(), this);
    }

    @SneakyThrows
    private void initCommands() {
        getCommand("SimpleStarterKits").setExecutor(new SimpleStarterKitsCommand());
    }

    @Override
    public void onDisable() {
    }
}