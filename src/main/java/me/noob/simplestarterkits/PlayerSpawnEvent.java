package me.noob.simplestarterkits;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.noob.simplestarterkits.SimpleStarterKits.giveKit;

public class PlayerSpawnEvent implements Listener {


    @EventHandler
    public void playerSpawnEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        giveKit(player, SimpleStarterKits.getStarterKit());
    }
}
