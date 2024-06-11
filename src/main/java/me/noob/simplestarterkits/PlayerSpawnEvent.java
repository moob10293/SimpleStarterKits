package me.noob.simplestarterkits;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerSpawnEvent implements Listener {

    @EventHandler
    public void playerSpawnEvent(@NotNull PlayerJoinEvent event) {
        //todo: figure out what's causing this to not work
        Player player = event.getPlayer();
        PlayedBeforeManager playedBeforeManager = SimpleStarterKits.getPlayedBeforeManager();
        ConfigurationManager pluginConfig = SimpleStarterKits.getConfigManager();

        if (playedBeforeManager.isNew(player)) playedBeforeManager.addPlayer(player);

        if (!pluginConfig.get("give", Boolean.class)) {
            //debug
            SimpleStarterKits.getStaticLogger().info("not giving");
            return;
        }

        String newIs = pluginConfig.get("new");
        if (newIs.equals("server")) {
            if (!player.hasPlayedBefore()) {
                SimpleStarterKits.giveKit(player);
            }
        } else if (newIs.equals("plugin")) {
            if (playedBeforeManager.isNew(player)) {
                SimpleStarterKits.giveKit(player);
            }
        } else {
            SimpleStarterKits.getStaticLogger().warning("Path 'new' in file config.yml can only be \"server\" or \"plugin\"!");
        }
    }
}
