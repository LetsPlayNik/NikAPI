package de.niklas.api.spigot;

import de.niklas.api.spigot.inventory.InventoryMenu;
import de.niklas.api.spigot.inventory.InventorySize;
import de.niklas.api.spigot.listeners.InventoryClickListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

public class API extends JavaPlugin {

    private HashMap<Inventory, InventoryMenu> inventoryGui = new HashMap();

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
        getServer().getPluginManager().registerEvents(new InventoryClickListener(this), this);
    }

    public HashMap<Inventory, InventoryMenu> getInventoryGuis() {
        return inventoryGui;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("test")) {
            InventoryMenu inventoryMenu = new InventoryMenu(InventorySize.threeXnine);
            return true;
        }
        return false;
    }
}
