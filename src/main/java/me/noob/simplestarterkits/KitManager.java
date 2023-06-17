package me.noob.simplestarterkits;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

public class KitManager extends ConfigurationManager {

    private final ConfigurationManager pluginConfig;
    private PlayerInventory starterKit;

    KitManager(SimpleStarterKits plugin, String fileName) {
        super(plugin, fileName);
        pluginConfig = SimpleStarterKits.getConfigManager();
        getStarterKit();
    }

    public void giveStarterKit(@NotNull Player player) {
        int index = 0;
        PlayerInventory playerInventory = player.getInventory();
        for (ItemStack itemStack : starterKit) {
            if (itemStack == null) {
                index++;
                continue;
            }
            if (playerInventory.getItem(index) != null) {
                player.sendMessage(pluginConfig.get("conflict-message"));
                Item item = player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
                item.setGlowing(true);
                continue;
            }
            player.getInventory().setItem(index, itemStack);
            index++;
        }
    }

    public void getStarterKit() {
        starterKit = get(pluginConfig.get("starter-kit-name"), PlayerInventory.class);
    }

    public void setStarterKit(PlayerInventory inventory) {
        starterKit = inventory;
        set(pluginConfig.get("starter-kit-name"), inventory);
    }

    @Override
    public void reload() {
        super.reload();
        getStarterKit();
    }
}
