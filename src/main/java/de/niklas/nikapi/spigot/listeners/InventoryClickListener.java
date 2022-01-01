package de.niklas.nikapi.spigot.listeners;

import de.niklas.nikapi.spigot.inventory.InventoryManager;
import de.niklas.nikapi.spigot.inventory.InventoryMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(event.getClickedInventory() == null) return;
        if(event.getAction() == null) return;
        if(event.getWhoClicked() instanceof Player) {
            if(InventoryManager.getInstance().getOpenedMenus().containsKey(event.getWhoClicked().getUniqueId())) {
                if(!event.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
                    InventoryMenu menu = InventoryManager.getInstance().getOpenedMenus().get(event.getWhoClicked().getUniqueId());
                    if(menu.getItems().containsKey(event.getSlot())) {
                        menu.getItems().get(event.getSlot()).click((Player) event.getWhoClicked());
                    }
                }
                event.setCancelled(true);
            }
        }
    }
}
