package de.niklas.api.spigot.inventory;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 29.12.2021 - 20:10Uhr
 */

import org.bukkit.inventory.ItemStack;

public class PaginatedInventoryMenu extends InventoryMenu {

    public PaginatedInventoryMenu(int size, String displayName) {
        super(size, displayName);
    }
    public PaginatedInventoryMenu(int size) {
        super(size);
    }

    public void setForwardItem(int index, ItemStack itemStack) {
        setItem(index, itemStack, player -> {
            System.out.println("Seite vorwärts.");
        });
    }
    public void setBackwardsItem(int index, ItemStack itemStack) {
        setItem(index, itemStack, player -> {
            System.out.println("Seite zurück.");
        });
    }
}
