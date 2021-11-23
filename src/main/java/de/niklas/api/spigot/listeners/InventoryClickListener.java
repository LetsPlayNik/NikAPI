package de.niklas.api.spigot.listeners;

import de.niklas.api.spigot.API;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    private API api;

    public InventoryClickListener(API api) {
        this.api = api;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(getApi().getInventoryGuis().containsKey(event.getInventory())) {
            event.setCancelled(true);
        }
    }

    public API getApi() {
        return api;
    }
}
