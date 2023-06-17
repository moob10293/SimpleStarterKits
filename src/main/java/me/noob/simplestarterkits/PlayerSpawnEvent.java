package me.noob.simplestarterkits;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerSpawnEvent implements Listener {

    @EventHandler
    public void playerSpawnEvent(@NotNull PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerManager playerManager = SimpleStarterKits.getPlayerManager();
        ConfigurationManager pluginConfig = SimpleStarterKits.getConfigManager();

        playerManager.addPlayer(player);
        if (!pluginConfig.get("give", Boolean.class)) {
            return;
        }

        String newIs = pluginConfig.get("new");
        if (newIs.equals("server")) {
            if (!player.hasPlayedBefore()) {
                SimpleStarterKits.giveKit(player);
            }
        } else if (newIs.equals("plugin")) {
            if (playerManager.isNew(player)) {
                SimpleStarterKits.giveKit(player);
            }
        } else {
            SimpleStarterKits.getInstance().getLogger().warning("Path 'new' in file config.yml can only be 'server' or 'plugin'!");
        }
    }
}
