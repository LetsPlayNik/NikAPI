package de.niklas.nikapi.spigot.listeners;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 26.01.2022 - 14:55Uhr
 */

import de.niklas.nikapi.spigot.inventory.InventoryManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if(InventoryManager.getInstance().getOpenedMenus().containsKey(event.getPlayer().getUniqueId())) {
            InventoryManager.getInstance().getOpenedMenus().remove(event.getPlayer().getUniqueId());
        }
    }
}
