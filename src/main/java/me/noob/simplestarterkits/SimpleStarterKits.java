package me.noob.simplestarterkits;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public final class SimpleStarterKits extends JavaPlugin {

    @Getter
    private static List<ItemStack> starterKit;

    @Getter
    private static YamlConfiguration kitsConfig;

    @Getter
    private static Logger logger;

    @Getter
    private static File kitsFile;

    public static void giveKit(Player player, String kit) {
        Set<String> slots = kitsConfig.getConfigurationSection(kit).getKeys(false);
        for (String key : slots) {
            int slot = Integer.parseInt(key);
            player.getInventory().setItem(slot, kitsConfig.getItemStack(String.format("%1s.%d",kit,slot)));
        }
    }

    public static void saveKit(@NotNull Player player, String kit){
        kitsConfig.set(kit,null);
        int i = 0;
        for (ItemStack itemstack : player.getInventory()){
            if (itemstack !=null){
                kitsConfig.set(String.format("%1s.%d",kit,i),itemstack);
            }
            i++;
        }

        try {
            kitsConfig.save(kitsFile);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("File 'kits.yml' could not be written to!");
        }
    }

    @Override
    public void onEnable() {
        logger=getLogger();
        logger.info("Enabling SimpleStarterKits");

        createConfigs();
        getCommand("simplestarterkits").setExecutor(new SimpleStarterKitsCommand());
        getServer().getPluginManager().registerEvents(new PlayerSpawnEvent(), this);

    }

    private void createConfigs() {
        saveDefaultConfig();
        kitsFile = new File(getDataFolder(), "kits.yml");
        saveResource("kits.yml", false);
        kitsConfig = YamlConfiguration.loadConfiguration(kitsFile);
    }

    @Override
    public void onDisable() {
    }
}