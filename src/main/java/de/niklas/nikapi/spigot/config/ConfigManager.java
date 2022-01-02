package de.niklas.nikapi.spigot.config;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 01.01.2022 - 16:35Uhr
 */

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConfigManager {

    private FileConfiguration config = null;
    private File configFile = null;
    private String fileName;

    /*KnockIt main;
    public ConfigManager(KnockIt knockIt, String fileName) {
        this.main = knockIt;
        this.fileName = fileName;
        saveDefaultConfig();
    }*/

    public void saveConfig() {
        if(config != null && configFile != null) {
            try {
                getConfig().save(configFile);
            } catch(IOException exception) {
                //System.out.println("NPCLib > Could not save Config.");
            }
        }
    }
    public void saveDefaultConfig() {
        if(configFile == null) {
            //configFile = new File(main.getDataFolder(), fileName);
        } else if(!configFile.exists()) {
            //main.saveResource(fileName, false);
        }
    }
    public void addDefault(String path, Object value) {
        if(!getConfig().contains(path)) {
            getConfig().set(path, value);
        }
    }
    public void reloadConfig() {
        if(configFile == null) {
            //configFile = new File(main.getDataFolder(), fileName);
        }
        config = YamlConfiguration.loadConfiguration(configFile);
        /*InputStream inputStream = main.getResource(fileName);
        if(inputStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(inputStream));
            config.setDefaults(defaultConfig);
        }*/
    }

    public FileConfiguration getConfig() {
        if(config == null) {
            reloadConfig();
        }
        return config;
    }
}
