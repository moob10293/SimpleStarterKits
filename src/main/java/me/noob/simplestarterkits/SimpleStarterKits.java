package me.noob.simplestarterkits;

import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public final class SimpleStarterKits extends JavaPlugin {
    @Getter
    private static SimpleStarterKits instance;
    @Getter
    private static List<ItemStack> starterKit;
    @Getter
    private YamlConfiguration kitsConfig;

    public static void giveKit(Player player, List<ItemStack> kit) {
        int index = 0;
        for (ItemStack itemStack : kit) {
            if (itemStack != null) {
                player.getInventory().setItem(index, itemStack);
            }
            index++;
        }
    }

    @Override
    public void onEnable() {
        getLogger().info("Enabling SimpleStarterKits...");
        instance = this;
        createConfigs();
        getCommand("simplestarterkits").setExecutor(new SimpleStarterKitsCommand());
        getServer().getPluginManager().registerEvents(new PlayerSpawnEvent(), this);

    }

    private void createConfigs() {
        this.saveDefaultConfig();
        File kitsFile = new File(getDataFolder(), "kits.yml");
        saveResource("kits.yml", false);
        kitsConfig = YamlConfiguration.loadConfiguration(kitsFile);
    }

    private void initStarterKit() {
        try {
            starterKit = (List<ItemStack>) kitsConfig.get(this.getConfig().getString("first-join-kit"));
        } catch (ClassCastException e) {
            e.printStackTrace();
            this.getLogger().info("Kit 'starter' in kits.yml could not be cast to an List<ItemStack>!");
        }
    }

    @Override
    public void onDisable() {
    }
}