package me.noob.starterkits;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

//use something.getdatafolder
public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new PlayerSpawnEvent(this.getConfig()), this);
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}