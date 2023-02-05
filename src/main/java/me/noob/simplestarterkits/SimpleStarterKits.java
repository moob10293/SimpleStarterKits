package me.noob.simplestarterkits;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.logging.Logger;

public final class SimpleStarterKits extends JavaPlugin {

    @Getter
    private static File kitsFile;

    @Getter
    private static YamlConfiguration kitsConfig;

    @Getter
    private static Logger logger;

    private static KitManager kitManager;

    @SneakyThrows
    public static void giveKit(Player player) {
        kitManager.giveKit(player);
    }

    public static void saveKit(@NotNull Player player) {
        kitManager.giveKit(player);
    }

    @Override
    public void onEnable() {
        getLogger().info("Enabling SimpleStarterKits...");
        init();
        getLogger().info("SimpleStarterKits Enabled.");

    }

    private void init() {
        logger = SimpleStarterKits.getLogger();
        kitManager = new KitManager(logger, kitsFile, kitsConfig);
        initConfigs();
        initCommands();
        initEvents();
    }

    private void initEvents() {
        getServer().getPluginManager().registerEvents(new PlayerSpawnEvent(), this);
    }

    @SneakyThrows
    private void initCommands() {
        getCommand("simplestarterkits").setExecutor(new SimpleStarterKitsCommand());
    }

    private void initConfigs() {
        saveDefaultConfig();
        kitsFile = new File(getDataFolder(), "kits.yml");
        saveResource("kits.yml", false);
        kitsConfig = YamlConfiguration.loadConfiguration(kitsFile);
    }

    @Override
    public void onDisable() {
    }
}