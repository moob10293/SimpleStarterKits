package me.noob.simplestarterkits;

import lombok.SneakyThrows;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class KitManager {

    private final SimpleStarterKits plugin;
    private final File file;
    private final Logger logger;
    private YamlConfiguration starterKit;

    KitManager(Logger logger, SimpleStarterKits plugin) {
        this.logger = logger;
        this.plugin = plugin;
        file = new File(plugin.getDataFolder(), "kits.yml");
        plugin.saveResource("kits.yml", false);
        starterKit = YamlConfiguration.loadConfiguration(file);
    }

    @SneakyThrows
    public void giveKit(Player player) {
        PlayerInventory starterKit = (PlayerInventory) this.starterKit.get("starter-kit");
        if (starterKit == null) {
            logger.info("Could not get starter kit from 'kits.yml'!");
            return;
        }

        int index = 0;
        for (ItemStack itemStack : starterKit) {
            if (itemStack != null) {
                player.getInventory().setItem(index, itemStack);
            }
            index++;
        }
    }

    public void saveKit(@NotNull Player player) {
        starterKit.set("starter-kit", player.getInventory());
        try {
            starterKit.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("Could not write to 'kits.yml'!");
        }
    }

    public void reload() {
        plugin.saveResource("kits.yml", false);
        starterKit = YamlConfiguration.loadConfiguration(file);
    }
}
