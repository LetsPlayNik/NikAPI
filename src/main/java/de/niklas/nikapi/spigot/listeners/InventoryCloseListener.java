package de.niklas.nikapi.spigot.listeners;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 23.12.2021 - 16:21Uhr
 */

import de.niklas.nikapi.spigot.inventory.InventoryManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if(InventoryManager.getInstance().getOpenedMenus().containsKey((Player) event.getPlayer())) {
            InventoryManager.getInstance().getOpenedMenus().remove((Player) event.getPlayer());
        }
    }
}
