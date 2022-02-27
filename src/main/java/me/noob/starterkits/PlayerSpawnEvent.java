package me.noob.starterkits;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public class PlayerSpawnEvent implements Listener {

    Configuration config;

    PlayerSpawnEvent(Configuration config) {
        this.config = config;
    }

    @EventHandler
    public void playerSpawnEvent(PlayerJoinEvent event) {
        List<Map<?, ?>> configMapList = config.getMapList("starterkit");
        Player player = event.getPlayer();
        player.sendMessage("hilo");
        if (!player.hasPlayedBefore()) {
            for (Map<?,?> map: configMapList){
                giveItem(player, Material.matchMaterial((String) map.get("material")), (int) map.get("amount"));
            }
            player.sendMessage("helo");
        }
    }

    private void giveItem(Player player, Material material, int amount){
        ItemStack itemStack = new ItemStack(material,amount);
        player.getInventory().addItem(itemStack);
    }
}