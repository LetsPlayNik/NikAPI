package de.niklas.api.spigot.inventory;

public class Inventory {

    private org.bukkit.inventory.Inventory inventory;

    /*public Inventory(InventorySize inventorySize, String displayName) {
        this.inventorySize = inventorySize;
        this.inventory = Bukkit.createInventory(null, getInventorySize(), displayName);
    }
    public Inventory(InventorySize inventorySize) {
        this.inventorySize = inventorySize;
        this.inventory = Bukkit.createInventory(null, getInventorySize());
    }
    public Inventory(InventoryType inventoryType, String displayName) {
        this.inventoryType = inventoryType;
        this.inventory = Bukkit.createInventory(null, getMinecraftInventoryType(), displayName);
    }
    public Inventory(InventoryType inventoryType) {
        this.inventoryType = inventoryType;
        this.inventory = Bukkit.createInventory(null, getMinecraftInventoryType());
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
    public org.bukkit.event.inventory.InventoryType getMinecraftInventoryType() {
        switch(inventoryType) {
            case CHEST:
                return org.bukkit.event.inventory.InventoryType.CHEST;
        }
        return org.bukkit.event.inventory.InventoryType.CHEST;
    }*/
}
