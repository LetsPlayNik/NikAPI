package de.niklas.api.spigot.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class Inventory {

    private final org.bukkit.inventory.Inventory inventory;

    public Inventory(int size, String displayName) {
        inventory = Bukkit.createInventory(null, size, displayName);
    }
    public Inventory(int size) {
        inventory = Bukkit.createInventory(null, size);
    }
    public Inventory(InventoryType inventoryType, String displayName) {
        inventory = Bukkit.createInventory(null, inventoryType, displayName);
    }
    public Inventory(InventoryType inventoryType) {
        inventory = Bukkit.createInventory(null, inventoryType);
    }

    public void open(Player player) {
        inventory.setItem(1, new ItemStack(Material.ACACIA_DOOR));
        player.openInventory(inventory);
    }

    public org.bukkit.inventory.Inventory getBukkitInventory() {
        return inventory;
    }
}
