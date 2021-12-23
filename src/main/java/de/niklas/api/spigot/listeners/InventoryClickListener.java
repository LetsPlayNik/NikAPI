package de.niklas.api.spigot.listeners;

import de.niklas.api.spigot.API;
import de.niklas.api.spigot.inventory.InventoryMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.List;

public class InventoryClickListener implements Listener {

    private final API api;

    private final List<InventoryMenu> inventoryMenus;

    public InventoryClickListener(API api) {
        this.api = api;
        inventoryMenus = new ArrayList<>();
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(getApi().getInventoryManager().getInventoryMenus().containsKey(event.getInventory())) {
            event.setCancelled(true);
        }
    }

    public List<InventoryMenu> getInventoryMenus() {
        return inventoryMenus;
    }

    public API getApi() {
        return api;
    }
}
