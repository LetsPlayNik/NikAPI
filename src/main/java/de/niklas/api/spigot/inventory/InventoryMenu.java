package de.niklas.api.spigot.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InventoryMenu {

    private InventoryType inventoryType;
    private InventorySize inventorySize;
    private org.bukkit.inventory.Inventory inventory;

    public InventoryMenu(InventorySize inventorySize, String displayName) {
        this.inventorySize = inventorySize;
        this.inventory = Bukkit.createInventory(null, getInventorySize(), displayName);
    }
    public InventoryMenu(InventorySize inventorySize) {
        this.inventorySize = inventorySize;
        this.inventory = Bukkit.createInventory(null, getInventorySize());
    }

    public void open(Player player) {
        player.openInventory(getInventory());
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getInventorySize() {
        switch(inventorySize) {
            case oneXnine:
                return 9;
            case twoXnine:
                return 18;
            case threeXnine:
                return 27;
            case fourXnine:
                return 36;
            case fiveXnine:
                return 45;
            case sixXnine:
                return 54;
            case sevenXnine:
                return 63;
        }
        return 9;
    }
}
