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
        List<ItemStack> firstJoinKit= SimpleStarterKits.getInstance().getStarterKit();
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            int index = 0;
            for (ItemStack itemStack: firstJoinKit){
                if (itemStack != null) {
                    player.getInventory().setItem(index,itemStack);
                }
                index++;
            }
        }
    }
}
