package de.niklas.nikapi.spigot.config;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 01.01.2022 - 16:49Uhr
 */

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class YAMLConfig {

    private final FileConfiguration config;
    private final File configFile;

    public YAMLConfig(String filename) throws IOException {
        configFile = new File(filename);
        if(!configFile.exists()) {
            //Test
            /*if(filename.contains("/")) {
                new File(filename.split("/")[filename.split("/").length - 1]).mkdirs();
            }*/
            configFile.getParentFile().mkdirs();
            //configFile.mkdirs();
            //Test END
            configFile.createNewFile();
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }
    /*public YAMLConfig(String subFolder, String filename) throws IOException {
        configFile = new File(subFolder, filename);
        if(!configFile.exists()) {
            new File(subFolder).mkdirs();
            configFile.createNewFile();
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }*/

    public void addEntry(String key, Object value) {
        getConfig().set(key, value);
    }
    public void addDefaultEntry(String key, Object value) {
        if(existEntry(key)) return;
        addEntry(key, value);
    }
    public void save() throws IOException {
        getConfig().save(configFile);
    }

    public String getString(String key) {
        return getConfig().getString(key);
    }
    public boolean getBoolean(String key) {
        return getConfig().getBoolean(key);
    }
    public int getInteger(String key) {
        return getConfig().getInt(key);
    }
    public double getDouble(String key) {
        return getConfig().getDouble(key);
    }
    public List<?> getList(String key) {
        return getConfig().getList(key);
    }
    public boolean existEntry(String key) {
        return getConfig().contains(key);
    }

    public FileConfiguration getConfig() {
        return config;
    }
}
