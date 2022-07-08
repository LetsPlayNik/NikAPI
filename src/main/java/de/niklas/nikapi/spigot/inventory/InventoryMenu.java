package de.niklas.nikapi.spigot.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class InventoryMenu {

    private final Inventory inventory;
    private final Map<Integer, InventoryMenuItem> items;
    private Consumer<InventoryClickEvent> clickListener;
    private boolean closeable;

    public InventoryMenu(int size, String displayName) {
        inventory = Bukkit.createInventory(null, size, displayName);
        items = new HashMap<>();
        closeable = true;
    }
    public InventoryMenu(int size) {
        inventory = Bukkit.createInventory(null, size);
        items = new HashMap<>();
        closeable = true;
    }

    public void open(Player player) {
        InventoryManager.getInstance().getOpenedMenus().put(player.getUniqueId(), this);
        player.openInventory(inventory);
        if(!InventoryManager.getInstance().getOpenedMenus().containsKey(player.getUniqueId())) {
            InventoryManager.getInstance().getOpenedMenus().put(player.getUniqueId(), this);
        }
    }
    public void close(Player player) {
        closeable = true;
        player.closeInventory();
    }
    public void setCloseable(boolean closeable) {
        this.closeable = closeable;
    }
    public boolean isCloseable() {
        return closeable;
    }
    public void setItem(int index, InventoryMenuItem inventoryMenuItem) {
        inventory.setItem(index, inventoryMenuItem.getItemStack());
        items.put(index, inventoryMenuItem);
    }
    public void setItem(int index, ItemStack itemStack) {
        setItem(index, new InventoryMenuItem(itemStack));
    }
    public void setItem(int index, ItemStack itemStack, Consumer<InventoryMenuAction> action) {
        setItem(index, new InventoryMenuItem(itemStack, action));
    }
    public void clearInventory() {
        inventory.clear();
        items.clear();
    }
    public void setSubMenuItem(int index, ItemStack itemStack, InventoryMenu inventoryMenu) {
        setItem(index, itemStack, action -> {
            inventoryMenu.open(action.getPlayer());
        });
    }
    public void setFillItem(ItemStack itemStack) {
        for(int i = 0; i < inventory.getSize(); i++) {
            if(inventory.getItem(i) == null) {
                setItem(i, itemStack);
            }
        }
    }
    public void setFillItem(ItemStack itemStack, Consumer<InventoryMenuAction> action) {
        for(int i = 0; i < inventory.getSize(); i++) {
            if(inventory.getItem(i) == null) {
                setItem(i, itemStack, action);
            }
        }
    }
    public void addClickListener(Consumer<InventoryClickEvent> event) {
        clickListener = event;
    }

    public Consumer<InventoryClickEvent> getClickListener() {
        return clickListener;
    }

    public Map<Integer, InventoryMenuItem> getItems() {
        return items;
    }

    public Inventory getBukkitInventory() {
        return inventory;
    }
    public InventoryMenu getInventoryMenu() {
        return this;
    }
}
