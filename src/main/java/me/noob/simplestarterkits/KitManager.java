package me.noob.simplestarterkits;

import lombok.SneakyThrows;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

public class KitManager {
    private final File file;
    private final YamlConfiguration starterKit;
    private final Logger logger;

    KitManager(Logger logger,File file,YamlConfiguration starterKit){
        this.logger =logger;
        this.file =file;
        this.starterKit =starterKit;
    }

    @SneakyThrows
    public void giveKit(Player player, String kit) {
        PlayerInventory inventory = (PlayerInventory) starterKit.get(kit);
        if (inventory==null){
            logger.info("Could not get inventory from 'kits.yml'!");
            return;
        }

        int index = 0;
        for (ItemStack itemStack : inventory) {
            if (itemStack != null) {
                player.getInventory().setItem(index, itemStack);
            }
            index++;
        }
    }

    public void saveKit(@NotNull Player player, String kit) {
        starterKit.set(kit,player.getInventory());
        try {
            starterKit.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("Could not write to 'kits.yml'!");
        }
    }
}
