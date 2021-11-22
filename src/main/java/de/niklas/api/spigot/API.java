package de.niklas.api.spigot;

import de.niklas.api.spigot.listeners.InventoryClickListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class API extends JavaPlugin {

    @Override
    public void onLoad() {
        getServer().getLogger().log(Level.INFO, "Plugin-API wird geladen...");
    }
    @Override
    public void onEnable() {
        initListeners();
        getServer().getLogger().log(Level.INFO, "Plugin-API wurde aktiviert.");
    }
    @Override
    public void onDisable() {
        getServer().getLogger().log(Level.INFO, "Plugin-API wurde deaktiviert.");
    }

    public void initListeners() {
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
    }
}
