package me.noob.simplestarterkits;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
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
    private static List<ItemStack> starterKit;

    @Override
    public void onEnable() {
        getLogger().info("Enabling SimpleStarterKits...");
        instance = this;
        createConfigs();
        getCommand("simplestarterkits").setExecutor(new SimpleStarterKitsCommand());
        getServer().getPluginManager().registerEvents(new PlayerSpawnEvent(), this);

    }

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
            starterKit = (List<ItemStack>) kitsConfig.get(config.getString("first-join-kit"));
        } catch (ClassCastException e) {
            e.printStackTrace();
            this.getLogger().info("Kit 'starter' in kits.yml could not be cast to an List<ItemStack>!");
        }
    }
    @Override
    public void onEnable() {
        getLogger().info("E");
        instance = this;
        createConfigs();

    public static void giveKit(Player player, List<ItemStack> kit) {
        int index = 0;
        for (ItemStack itemStack: kit){
            if (itemStack != null) {
                player.getInventory().setItem(index,itemStack);
            }
            index++;
        }
    }
    @Override
    public void onDisable() {
    }
}