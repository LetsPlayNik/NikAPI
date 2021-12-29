package de.niklas.api.spigot.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class InventoryMenu {

    private final Inventory inventory;
    private final Map<Integer, Consumer<Player>> actions;

    public InventoryMenu(int size, String displayName) {
        inventory = Bukkit.createInventory(null, size, displayName);
        actions = new HashMap<>();
    }
    public InventoryMenu(int size) {
        inventory = Bukkit.createInventory(null, size);
        actions = new HashMap<>();
    }

    public void open(Player player) {
        if(InventoryManager.getInstance().getOpenedMenus().containsKey(player)) {
            player.closeInventory();
        }
        InventoryManager.getInstance().getOpenedMenus().put(player, this);
        player.openInventory(inventory);
    }
    public void setItem(int index, ItemStack itemStack) {
        inventory.setItem(index, itemStack);
    }
    public void setItem(int index, ItemStack itemStack, Consumer<Player> consumer) {
        inventory.setItem(index, itemStack);
        actions.put(index, consumer);
    }
    public void setSubMenuItem(int index, ItemStack itemStack, InventoryMenu inventoryMenu) {
        setItem(index, itemStack, inventoryMenu::open);
    }
    public void setFillItem(ItemStack itemStack) {
        for(int i = 0; i < inventory.getSize(); i++) {
            if(inventory.getItem(i) == null) {
                inventory.setItem(i, itemStack);
            }
        }
    }
    public void click(Player player, int slot) {
        if(actions.containsKey(slot)) {
            actions.get(slot).accept(player);
        }
    }
}
