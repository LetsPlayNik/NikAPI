package de.niklas.api.spigot.inventory;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 29.12.2021 - 20:10Uhr
 */

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class PaginatedInventoryMenu extends InventoryMenu {

    private int pageCount = 1;
    private final Map<Integer, InventoryMenu> pages;
    private int currentPage = 1;

    public PaginatedInventoryMenu(int size, String displayName) {
        super(size, displayName);
        pages = new HashMap<>();
    }
    public PaginatedInventoryMenu(int size) {
        super(size);
        pages = new HashMap<>();
    }

    public void setForwardItem(int index, ItemStack itemStack) {
        setItem(index, itemStack, player -> {
            if(currentPage < pageCount) {
                currentPage++;
                pages.get(currentPage).open(player);
                System.out.println("Seite vorwärts.");
            }
        });
    }
    public void setBackwardsItem(int index, ItemStack itemStack) {
        setItem(index, itemStack, player -> {
            if(currentPage > 1) {
                currentPage--;
                pages.get(currentPage).open(player);
                System.out.println("Seite zurück.");
            }
        });
    }
    public void addPage(InventoryMenu inventoryMenu) {
        pageCount++;
        pages.put(pageCount, inventoryMenu);
    }
}
