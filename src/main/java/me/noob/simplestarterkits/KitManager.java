package me.noob.simplestarterkits;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class KitManager extends ConfigurationManager {

    private ConfigurationManager pluginConfig;
    private List<ItemStack> starterKit = new ArrayList<>();

    public KitManager(String fileName, @NotNull JavaPlugin plugin) {
        super(fileName, plugin);
    }

    public void giveStarterKit(@NotNull Player player) {
        int index = 0;
        boolean messaged = false;
        PlayerInventory playerInventory = player.getInventory();
        for (ItemStack itemStack : starterKit) {
//          empty itemstacks are random btwn null and air
            if (itemStack == null||itemStack.getType()== Material.AIR) {
                index++;
                continue;
            }
//            already checks if null
//            noinspection DataFlowIssue
            if (playerInventory.getItem(index) == null || playerInventory.getItem(index).getType() == Material.AIR) {
                player.getInventory().setItem(index, itemStack);
                index++;
                continue;
            }
            if (!messaged) {
                player.sendMessage(pluginConfig.get("conflict-message"));
                messaged=true;
            }
            Item item = player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
            item.setGlowing(true);
            index++;
        }
    }

    public void setStarterKit(PlayerInventory inventory) {
//      todo: replace with actual deep copy in case of bugs like amount still carrying over
        starterKit = Arrays.stream(inventory.getContents())
                .map(item -> item != null ? item.clone() : null)
                .toList();
        set(pluginConfig.get("starter-kit-name"), starterKit);
    }

    @Override
    public void reload() {
        super.reload();
        pluginConfig = SimpleStarterKits.getConfigManager();
        // TODO: Replace with simpler list comprehension idk how
//        this throws error if it's null or can't cast
//        I have no idea how it works
        starterKit = Objects.requireNonNull(config.getList(pluginConfig.get("starter-kit-name")))
                .stream()
                .map(ItemStack.class::cast)
                .collect(Collectors.toList());
//        this causes warnings for some reason
//        try {
//            starterKit = (List<ItemStack>) config.getList(pluginConfig.get("starter-kit-name"));
//        } catch (ClassCastException e){
//            e.printStackTrace();
//            logger.severe("Path '" + pluginConfig.get("starter-kit-name") + "' in file " + fileName + " is not a list!");
//        }
    }
}
