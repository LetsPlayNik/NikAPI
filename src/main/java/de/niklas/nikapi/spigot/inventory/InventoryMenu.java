package de.niklas.nikapi.spigot.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class InventoryMenu {

    private final Inventory inventory;
    private final Map<Integer, ItemStack> items; //test
    private final Map<Integer, Consumer<Player>> actions;

    public InventoryMenu(int size, String displayName) {
        inventory = Bukkit.createInventory(null, size, displayName);
        items = new HashMap<>(); //test
        actions = new HashMap<>();
    }
    public InventoryMenu(int size) {
        inventory = Bukkit.createInventory(null, size);
        items = new HashMap<>(); //test
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
        items.put(index, itemStack); //test
    }
    public void setItem(int index, ItemStack itemStack, Consumer<Player> consumer) {
        inventory.setItem(index, itemStack);
        items.put(index, itemStack); //test
        actions.put(index, consumer);
    }
    public void clearInventory() {
        inventory.clear();
        items.clear(); //test
    }
    public void setSubMenuItem(int index, ItemStack itemStack, InventoryMenu inventoryMenu) {
        setItem(index, itemStack, inventoryMenu::open);
    }
    public void setFillItem(ItemStack itemStack) {
        for(int i = 0; i < inventory.getSize(); i++) {
            if(inventory.getItem(i) == null) {
                inventory.setItem(i, itemStack);
                items.put(i, itemStack); //test
            }
        }
    }
    public void click(Player player, int slot) {
        if(actions.containsKey(slot)) {
            actions.get(slot).accept(player);
        }
    }

    public InventoryMenu getInventoryMenu() {
        return this;
    }


    public Map<Integer, ItemStack> getItems() {
        return items;
    }
}
