package me.moob.hardersurvival;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class PlayerSpawnEvent implements Listener {

    Plugin plugin;

    PlayerSpawnEvent(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void playerSpawnEvent(PlayerJoinEvent event) {
        event.getplayer'
        if (player.get) {
            Player player = event.getPlayer();
            giveItem(player, Material.OAK_LOG, 32);
            giveItem(player, Material.COBBLESTONE, 64);
            giveItem(player, Material.COAL, 8);
            giveItem(player, Material.BREAD, 64);
            player.sendMessage("here you go");
