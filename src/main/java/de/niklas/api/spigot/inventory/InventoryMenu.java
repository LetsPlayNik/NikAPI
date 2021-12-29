package de.niklas.api.spigot.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class InventoryMenu {

    private final org.bukkit.inventory.Inventory inventory;

    public InventoryMenu(int size, String displayName) {
        inventory = Bukkit.createInventory(null, size, displayName);
    }
    public InventoryMenu(int size) {
        inventory = Bukkit.createInventory(null, size);
    }

    public void open(Player player) {
        InventoryManager.getInstance().getOpenedMenus().put(player, this);
        player.openInventory(inventory);
    }
    public void setItem(int index, ItemStack itemStack) {
        inventory.setItem(index, itemStack);
    }
    public void setItem(int index, ItemStack itemStack, Consumer<Player> player) {
        inventory.setItem(index, itemStack);
    }
    public void click(Player player, int slot) {

    }
}
