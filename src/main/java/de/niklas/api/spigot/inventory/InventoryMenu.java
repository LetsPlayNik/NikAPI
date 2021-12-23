package de.niklas.api.spigot.inventory;

public class InventoryMenu extends Inventory {

    public InventoryMenu(int size, String displayName) {
        super(size, displayName);
        InventoryManager.getInstance().getInventoryMenus().put(getBukkitInventory(), this);
    }
}
