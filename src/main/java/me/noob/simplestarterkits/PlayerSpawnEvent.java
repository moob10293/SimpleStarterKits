package me.noob.simplestarterkits;

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


    @EventHandler
    public void playerSpawnEvent(PlayerJoinEvent event) {
        List<Map<?, ?>> mapList = SimpleStarterKits.getInstance().getSettings().getMapList("starterkit");
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            for (Map<?,?> map: mapList){
                giveItem(player, Material.matchMaterial((String) map.get("material")), (int) map.get("amount"));
            }
        }
    }

    private static void giveItem(Player player, Material material, int amount){
        player.getInventory().addItem(new ItemStack(material,amount));
    }
}
