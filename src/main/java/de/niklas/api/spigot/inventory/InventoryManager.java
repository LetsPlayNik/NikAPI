package de.niklas.api.spigot.inventory;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 23.12.2021 - 15:26Uhr
 */

import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager {

    private final Map<Inventory, InventoryMenu> inventoryMenus;

    public InventoryManager() {
        inventoryMenus = new HashMap<>();
    }

    public Map<Inventory, InventoryMenu> getInventoryMenus() {
        return inventoryMenus;
    }
}
