package me.noob.simplestarterkits;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

public final class SimpleStarterKits extends JavaPlugin {
    @Getter
    private static SimpleStarterKits instance;
    @Getter
    private List<ItemStack> starterKit;
    @Getter
    private YamlConfiguration kitsConfig;

    File kitsFile;
    public void giveKit(Player player, String kit) {
        PlayerInventory inventory = (PlayerInventory) kitsConfig.get(kit);
        int index = 0;
        for (ItemStack itemStack : inventory) {
            if (itemStack != null) {
                player.getInventory().setItem(index, itemStack);
            }
            index++;
        }
    }

    public void saveKit(@NotNull Player player, String kit){
        kitsConfig.set(kit,player.getInventory());
        try {
            kitsConfig.save(kitsFile);
        } catch (IOException e) {
            e.printStackTrace();
            getLogger().info("File 'kits.yml' could not be written to!");
        }
    }

    @Override
    public void onEnable() {
        getLogger().info("Enabling SimpleStarterKits...");
        instance = this;
        createConfigs();
        initStarterKit();
        getCommand("simplestarterkits").setExecutor(new SimpleStarterKitsCommand());
        getServer().getPluginManager().registerEvents(new PlayerSpawnEvent(), this);

    }

    private void createConfigs() {
        saveDefaultConfig();
        kitsFile = new File(getDataFolder(), "kits.yml");
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