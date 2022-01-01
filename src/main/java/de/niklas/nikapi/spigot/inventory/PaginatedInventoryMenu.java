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

    //private List<InventoryMenuItem> items = new ArrayList<>();

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

    /*public void addItem(ItemStack itemStack, Consumer<Player> consumer) {
        items.add(new InventoryMenuItem(itemStack, consumer));

    }*/
    /*public void addItem(ItemStack itemStack) {
        //Test
        int page = 1;
        boolean needSite = true;
        while(needSite) {
            System.out.println("Debug 1");
            System.out.println("Page:) " + page);
            if(pages.containsKey(page)) {
                System.out.println("Debug 2");
                InventoryMenu menu = pages.get(page);
                System.out.println("Debug 2");
                //Test
                for(int i = 0; i < menu.getBukkitInventory().getSize(); i++) {
                    System.out.println("Debug 3");
                    if(i != backwardsItemIndex && menu.getBukkitInventory().getItem(i) == null) {
                        System.out.println("Debug 4");
                        menu.setItem(i, itemStack);
                        needSite = false;
                        break;
                    } else {
                        System.out.println("Debug 5");
                        if(i == menu.getBukkitInventory().getSize() - 1) {
                            System.out.println("Debug 6");
                            //Test
                            InventoryMenu newMenu = new InventoryMenu(menu.getBukkitInventory().getSize(), menu.getBukkitInventory().getName());
                            newMenu.setItem(1, menu.getBukkitInventory().getItem(i));
                            addPage(newMenu);
                            page++;
                            System.out.println("Neue Seite!");
                            System.out.println("Page: " + page);
                            System.out.println("Page Size: " + pages.size());
                            break;
                            //TODO create new page.
                            //Test END
                        }
                    }
                }
                //Test END
            } else if(page == 1) {
                for(int i = 0; i < getBukkitInventory().getSize(); i++) {
                    if(pages.size() > 0) {
                        if(i != forwardItemIndex && getBukkitInventory().getItem(i) == null) {
                            setItem(i, itemStack);
                            needSite = false;
                            break;
                        }
                    } else {
                        if(getBukkitInventory().getItem(i) == null) {
                            setItem(i, itemStack);
                            needSite = false;
                            break;
                        } else {
                            if(i == getBukkitInventory().getSize() - 1) {
                                InventoryMenu newMenu = new InventoryMenu(getBukkitInventory().getSize(), getBukkitInventory().getName());
                                newMenu.setItem(1, getBukkitInventory().getItem(i));
                                addPage(newMenu);
                                page++;
                                System.out.println("Page:( " + page);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }*/
    //public void addItem(ItemStack itemStack, Consumer<Player> consumer) {}
    public void addPage(InventoryMenu inventoryMenu) {
        pages.put(pages.size() + 2, inventoryMenu);
    }

    /*@Override
    public void open(Player player) {
        List<InventoryMenuItem> menuItems = new ArrayList<>();
        int i = 0;
        int startItem = 1;
        int invSize = getBukkitInventory().getSize();
        int currentPageSize = invSize - 2;
        int page = 1;
        int slot = 0;
        if(items.size() < invSize) {
        }
        if(currentPage == 1) {
            currentPageSize++;
            if(items.size() <= invSize) {
                currentPageSize++;
            }
        } else {
        }
        while(i < getBukkitInventory().getSize()) {
            i++;
        }
        super.open(player);
    }*/


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
                    /*if(currentPage == 2) {
                        currentPage--;
                        open(p);
                    } else */if(currentPage > 1) {
                        currentPage--;
                        pages.get(currentPage).open(p);
                    }
                });
                pages.remove(key);
                pages.put(key, inventoryMenu);
            }
            //Test
            /*InventoryMenu inventoryMenu = pages.get(key);
            if(key != pages.size()) {
                inventoryMenu.setItem(forwardItemIndex, forwardItem, p -> {
                    if(currentPage < pages.size()) {
                        currentPage++;
                        pages.get(currentPage).open(p);
                    }
                });
            }
            inventoryMenu.setItem(backwardsItemIndex, backwardsItem, p -> {
                if(currentPage == 2) {
                    currentPage--;
                    open(p);
                } else if(currentPage > 1) {
                    currentPage--;
                    pages.get(currentPage).open(p);
                }
            });
            pages.remove(key);
            pages.put(key, inventoryMenu);*/
            //Test END
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
