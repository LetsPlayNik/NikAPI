package de.niklas.api.spigot.inventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class InventoryMenu extends Inventory {

    public InventoryMenu(int size, String displayName) {
        super(size, displayName);
    }

    @Override
    public void open(Player player) {
        InventoryManager.getInstance().getOpenedMenus().put(player, this);
        super.open(player);
    }

    public void setItem(int index, ItemStack itemStack, Consumer<Player> player) {
    }
    public void click(Player player, int slot) {

    }
}
