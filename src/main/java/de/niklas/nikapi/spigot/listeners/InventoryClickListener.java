package de.niklas.nikapi.spigot.listeners;

import de.niklas.nikapi.spigot.inventory.InventoryManager;
import de.niklas.nikapi.spigot.inventory.InventoryMenu;
import de.niklas.nikapi.spigot.inventory.InventoryMenuAction;
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
                    if(menu.getClickListener() != null) {
                        menu.getClickListener().accept(event);
                    }
                    if(menu.getItems().containsKey(event.getSlot())) {
                        if(menu.getItems().get(event.getSlot()).getAction() != null) {
                            menu.getItems().get(event.getSlot()).getAction().accept(new InventoryMenuAction((Player) event.getWhoClicked(), event.isLeftClick(), event.isRightClick(), event.isShiftClick()));
                        }
                    }
                }
                event.setCancelled(true);
            }
        }
    }
}
