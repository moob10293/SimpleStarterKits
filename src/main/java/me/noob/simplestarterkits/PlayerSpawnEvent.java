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

import static me.noob.simplestarterkits.SimpleStarterKits.giveKit;

public class PlayerSpawnEvent implements Listener {


    @EventHandler
    public void playerSpawnEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        giveKit(player, SimpleStarterKits.getStarterKit());
    }
}
