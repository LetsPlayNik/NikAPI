package de.niklas.nikapi.spigot.inventory;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 31.12.2021 - 16:43Uhr
 */

import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class InventoryMenuItem {

    private final ItemStack itemStack;
    private final Consumer<InventoryMenuAction> action;

    public InventoryMenuItem(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.action = null;
    }
    public InventoryMenuItem(ItemStack itemStack, Consumer<InventoryMenuAction> action) {
        this.itemStack = itemStack;
        this.action = action;
    }

    public Consumer<InventoryMenuAction> getAction() {
        return action;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
