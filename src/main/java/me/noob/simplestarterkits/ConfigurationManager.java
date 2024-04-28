package me.noob.simplestarterkits;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class ConfigurationManager {

    protected final File file;
    protected final JavaPlugin plugin;
    protected final Logger logger;
    protected final String fileName;
    protected YamlConfiguration config;

    public ConfigurationManager(String fileName, @NotNull JavaPlugin plugin) {
        this.fileName = fileName;
        this.plugin = plugin;
        this.logger = plugin.getLogger();
        file = new File(plugin.getDataFolder(), fileName);
        reload();
    }

    public String get(String path) {
        return get(path, String.class);
    }

    /**
     * Wrapper for config.getObject that gives error messages
     **/
    public <T> T get(String path, Class<T> clazz) {
        if (pathNotSet(path)) return null;

        T object = config.getObject(path, clazz);

        if (object == null) {
            logger.warning("Path '" + path + "' in file " + fileName + " is not a '" + clazz.getSimpleName() + "'!");
        }

        return object;
    }

    /**
     * Logs a warning message if there is nothing at the path
     * @param path The path to the object
     * @return A boolean indicating if there is anything in the config where the path points
     */
    protected boolean pathNotSet(String path) {
        if (!config.isSet(path)) {
            logger.warning("Path '" + path + "' in file " + fileName + " is not set!");
            return true;
        }
        return false;
    }

    public void set(String path, Object value) {
        config.set(path, value);
        saveToFile();
    }

    /**
     * get config from file
     **/
    public void reload() {
        if (!file.exists()) {
            plugin.saveResource(fileName, false);
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    protected void saveToFile() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            logger.warning("Could not write to '" + this.fileName + "'!");
        }
    }
}
