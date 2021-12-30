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
        pages.put(pages.size() + 2, inventoryMenu);
    }
    /*@Override
    public void addItemStacks(List<ItemStack> items) {
        int site = 1;
        items.forEach(item -> {
            boolean needSite = true;
            for(int i = 0; i < getBukkitInventory().getSize(); i++) {
                if(i != forwardItemIndex && i != backwardsItemIndex) {
                    if(getBukkitInventory().getItem(i) == null) {
                        setItem(i, item);
                        needSite = false;
                        break;
                    }
                }
            }
        });
    }
    @Override
    public void addItemStacks(Map<ItemStack, Consumer<Player>> items) {
        int site = 1;
        for(ItemStack key : items.keySet()) {
            boolean needSite = true;
            for(int i = 0; i < getBukkitInventory().getSize(); i++) {
                if(i != forwardItemIndex && i != backwardsItemIndex) {
                    if(getBukkitInventory().getItem(i) == null) {
                        setItem(i, key, items.get(key));
                        needSite = false;
                        break;
                    }
                }
            }
        }
    }*/

    @Override
    @Deprecated
    public void open(Player player) {
        pages.put(1, getInventoryMenu());
        if(forwardItem != null || backwardsItem != null) {
            for(Integer key : pages.keySet()) {
                if(key != 1) {
                    InventoryMenu inventoryMenu = pages.get(key);
                    if(forwardItem != null && key != pages.size()) {
                        inventoryMenu.setItem(forwardItemIndex, forwardItem, p -> {
                            if(currentPage < pages.size()) {
                                currentPage++;
                                pages.get(currentPage).open(p);
                            }
                        });
                    }
                    if(backwardsItem != null) {
                        inventoryMenu.setItem(backwardsItemIndex, backwardsItem, p -> {
                            if(currentPage > 1) {
                                currentPage--;
                                pages.get(currentPage).open(p);
                            }
                        });
                    }
                    pages.remove(key);
                    pages.put(key, inventoryMenu);
                }
            }
        }
        super.open(player);
    }
}
