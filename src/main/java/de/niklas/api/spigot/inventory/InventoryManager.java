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

    private static InventoryManager instance;

    private final Map<Inventory, InventoryMenu> inventoryMenus;

    public InventoryManager() {
        instance = this;
        inventoryMenus = new HashMap<>();
    }

    public Map<Inventory, InventoryMenu> getInventoryMenus() {
        return inventoryMenus;
    }

    public static InventoryManager getInstance() {
        return instance;
    }
}