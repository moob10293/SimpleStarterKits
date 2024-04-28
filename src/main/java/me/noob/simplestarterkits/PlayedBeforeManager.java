package me.noob.simplestarterkits;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PlayedBeforeManager extends ConfigurationManager {

    private List<String> playedBefore = new ArrayList<>();

    PlayedBeforeManager(String fileName, JavaPlugin plugin) {
        super(fileName, plugin);
    }

    public boolean isNew(@NotNull Player player) {
        return !playedBefore.contains(player.getName());
    }

    public void addPlayer(@NotNull Player player) {
        playedBefore.add(player.getName());
        set("played-before", playedBefore);
    }

    @Override
    public void reload() {
        super.reload();
        if (pathNotSet("played-before")){
            logger.warning("Setting played-before to an empty list!");
            set("played-before", playedBefore);
            return;
        }
        playedBefore = config.getStringList("played-before");
    }
}
