package me.noob.simplestarterkits;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PlayerManager extends ConfigurationManager {

    private List<String> playedBefore;

    PlayerManager(JavaPlugin plugin, String fileName) {
        super(plugin, fileName);
    }

    public boolean isNew(@NotNull Player player) {
        return !playedBefore.contains(player.getName());
    }

    public void addPlayer(@NotNull Player player) {
        playedBefore.add(player.getName());
        set("played-before", playedBefore);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void reload() {
        super.reload();
        try {
            playedBefore = (List<String>) get("played-before", List.class);
        } catch (ClassCastException e) {
            logger.warning("Path 'played-before' in file played_before.yml is not a list of players!");
        }
    }
}
