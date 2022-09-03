package me.noob.simplestarterkits;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public class SimpleStarterKits extends JavaPlugin {
    @Getter
    private static SimpleStarterKits instance;
    @Getter
    private FileConfiguration config;
    @Getter
    private YamlConfiguration kitsConfig;
    @Getter
    private List<ItemStack> starterKit;

    private void createConfigs() {
        File kitsFile = new File(getDataFolder(), "kits.yml");
        if (!kitsFile.exists()) {
            kitsFile.getParentFile().mkdirs();
            saveResource("kits.yml", false);
        }

        kitsConfig = YamlConfiguration.loadConfiguration(kitsFile);
        this.saveDefaultConfig();
    }
    private void initStarterKit() {
        try {
            starterKit = (List<ItemStack>) kitsConfig.get(config.getString("FirstJoinKit"));
        } catch (ClassCastException e) {
            e.printStackTrace();
            this.getLogger().info("StarterKit in kits.yml could not be cast to an List<ItemStack>!");
        }
    }
    @Override
    public void onEnable() {
        getLogger().info("E");
        instance = this;
        createConfigs();

        getServer().getPluginManager().registerEvents(new PlayerSpawnEvent(), this);
    }
    @Override
    public void onDisable() {
    }
}