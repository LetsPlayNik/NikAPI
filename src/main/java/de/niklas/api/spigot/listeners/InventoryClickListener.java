package de.niklas.api.spigot.listeners;

import de.niklas.api.spigot.API;
import de.niklas.api.spigot.inventory.InventoryManager;
import de.niklas.api.spigot.inventory.InventoryMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    private final API api;

    public InventoryClickListener(API api) {
        this.api = api;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(event.getWhoClicked() instanceof Player) {
            if(InventoryManager.getInstance().getOpenedMenus().containsKey((Player) event.getWhoClicked())) {
                InventoryMenu menu = InventoryManager.getInstance().getOpenedMenus().get((Player) event.getWhoClicked());
                menu.click((Player) event.getWhoClicked(), event.getSlot());
                event.setCancelled(true);
            }
        }
    }

    public API getApi() {
        return api;
    }
}
