package me.noob.simplestarterkits;

import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class KitManager {

    private final File file;
    private final Logger logger;
    private YamlConfiguration kitConfig;

    KitManager(Logger logger, @NotNull SimpleStarterKits plugin) {
        this.logger = logger;
        file = new File(plugin.getDataFolder(), "kits.yml");
        plugin.saveResource("kits.yml", false);
        kitConfig = YamlConfiguration.loadConfiguration(file);
    }

    @SneakyThrows
    public void give(Player player) {
        if (!kitConfig.isSet("starter-kit")) {
            logger.info("The starter kit is not set!");
            return;
        }
        PlayerInventory starterInventory = this.kitConfig.getObject("starter-kit", PlayerInventory.class);
        if (starterInventory == null) {
            logger.info("Could not get starter kit from 'kits.yml'!");
            return;
        }

        int index = 0;
        for (ItemStack itemStack : starterInventory) {
            if (itemStack != null) {
                player.getInventory().setItem(index, itemStack);
            }
            index++;
        }
    }

    public void set(@NotNull Player player) {
        kitConfig.set("starter-kit", player.getInventory());
        try {
            kitConfig.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("Could not write to 'kits.yml'!");
        }
    }

    public void clear() {
        kitConfig.set("starter-kit", Bukkit.createInventory(null, InventoryType.PLAYER));
        try {
            kitConfig.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("Could not write to 'kits.yml'!");
        }
    }

    public void reload() {
        kitConfig = YamlConfiguration.loadConfiguration(file);
    }
}
