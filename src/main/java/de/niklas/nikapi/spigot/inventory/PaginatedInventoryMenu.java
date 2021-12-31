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
import java.util.function.Consumer;

public class PaginatedInventoryMenu extends InventoryMenu {

    private final Map<Integer, InventoryMenu> pages;
    private int currentPage = 1;
    private final int backwardsItemIndex;
    private final ItemStack backwardsItem;
    private final int forwardItemIndex;
    private final ItemStack forwardItem;

    public PaginatedInventoryMenu(int size, String displayName, int backwardsItemIndex, ItemStack backwardsItem, int forwardItemIndex, ItemStack forwardItem) {
        super(size, displayName);
        pages = new ConcurrentHashMap<>();
        this.backwardsItemIndex = backwardsItemIndex;
        this.backwardsItem = backwardsItem;
        this.forwardItemIndex = forwardItemIndex;
        this.forwardItem = forwardItem;
    }
    public PaginatedInventoryMenu(int size, int backwardsItemIndex, ItemStack backwardsItem, int forwardItemIndex, ItemStack forwardItem) {
        super(size);
        pages = new ConcurrentHashMap<>();
        this.backwardsItemIndex = backwardsItemIndex;
        this.backwardsItem = backwardsItem;
        this.forwardItemIndex = forwardItemIndex;
        this.forwardItem = forwardItem;
    }

    /*public void addItem(ItemStack itemStack) {}
    public void addItem(ItemStack itemStack, Consumer<Player> consumer) {}*/
    public void addPage(InventoryMenu inventoryMenu) {
        pages.put(pages.size() + 2, inventoryMenu);
    }

    @Override
    public void open(Player player) {
        pages.put(1, getInventoryMenu());
        if(pages.size() > 1) {
            setItem(forwardItemIndex, forwardItem, p -> {
                if(currentPage < pages.size()) {
                    currentPage++;
                    pages.get(currentPage).open(p);
                }
            });
        }
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
}
