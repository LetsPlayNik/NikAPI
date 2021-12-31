package de.niklas.nikapi.spigot.inventory;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 31.12.2021 - 16:43Uhr
 */

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class InventoryMenuItem {

    private final ItemStack itemStack;
    private final Consumer<Player> action;

    public InventoryMenuItem(ItemStack itemStack, Consumer<Player> action) {
        this.itemStack = itemStack;
        this.action = action;
    }

    public Consumer<Player> getAction() {
        return action;
    }
    public ItemStack getItemStack() {
        return itemStack;
    }
}
