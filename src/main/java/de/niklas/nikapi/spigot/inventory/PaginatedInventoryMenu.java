package de.niklas.nikapi.spigot.inventory;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 29.12.2021 - 20:10Uhr
 */

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PaginatedInventoryMenu extends InventoryMenu {

    private int pageCount = 1;
    private int forwardItemIndex;
    private ItemStack forwardItem;
    private int backwardsItemIndex;
    private ItemStack backwardsItem;
    private final Map<Integer, InventoryMenu> pages;
    private int currentPage = 1;

    public PaginatedInventoryMenu(int size, String displayName) {
        super(size, displayName);
        pages = new ConcurrentHashMap<>();
    }
    public PaginatedInventoryMenu(int size) {
        super(size);
        pages = new ConcurrentHashMap<>();
    }

    public void setForwardItem(int index, ItemStack itemStack) {
        forwardItemIndex = index;
        forwardItem = itemStack;
        setItem(index, itemStack, player -> {
            if(currentPage < pages.size()) {
                currentPage++;
                pages.get(currentPage).open(player);
            }
        });
    }
    public void setBackwardsItem(int index, ItemStack itemStack) {
        backwardsItemIndex = index;
        backwardsItem = itemStack;
    }
    public void addPage(InventoryMenu inventoryMenu) {
        pageCount++;
        pages.put(pageCount, inventoryMenu);
    }

    @Override
    public void open(Player player) {
        pages.put(1, getInventoryMenu());
        for(Integer key : pages.keySet()) {
            if(key != 1) {
                InventoryMenu inventoryMenu = pages.get(key);
                if(key != pages.size()) {
                    inventoryMenu.setItem(forwardItemIndex, forwardItem, p -> {
                        if(currentPage < pages.size()) {
                            currentPage++;
                            pages.get(currentPage).open(p);
                        }
                    });
                }
                inventoryMenu.setItem(backwardsItemIndex, backwardsItem, p -> {
                    if(currentPage > 1) {
                        currentPage--;
                        pages.get(currentPage).open(p);
                    }
                });
                pages.remove(key);
                pages.put(key, inventoryMenu);
            }
        }
        super.open(player);
    }
}
