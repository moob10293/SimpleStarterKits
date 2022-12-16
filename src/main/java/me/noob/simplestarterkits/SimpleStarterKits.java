package me.noob.simplestarterkits;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public final class SimpleStarterKits extends JavaPlugin {

    @Getter
    private static File kitsFile;

    @Getter
    private static YamlConfiguration kitsConfig;

    @Getter
    private static Logger staticLogger;

    @SneakyThrows
    public static boolean giveKit(Player player, String kit) {
        if (!kitsConfig.contains(kit)) {
            return false;
        }
        Set<String> slots = kitsConfig.getConfigurationSection(kit).getKeys(false);
        for (String key : slots) {
            int slot = Integer.parseInt(key);
            player.getInventory().setItem(slot, kitsConfig.getItemStack(String.format("%1s.%d", kit, slot)));
        }
        return true;
    }

    public static void saveKit(@NotNull Player player, String kit) {
        kitsConfig.set(kit, null);
        int i = 0;
        for (ItemStack itemstack : player.getInventory()) {
            if (itemstack != null) {
                kitsConfig.set(String.format("%1s.%d", kit, i), itemstack);
            }
            i++;
        }

        try {
            kitsConfig.save(kitsFile);
        } catch (IOException e) {
            e.printStackTrace();
            staticLogger.info("Could not write to 'kits.yml'!");
        }
    }

    @Override
    public void onEnable() {
        getLogger().info("Enabling SimpleStarterKits.");

        init();

    }

    private void init() {
        staticLogger=this.getLogger();
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