package de.niklas.nikapi.spigot.item.custom;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 31.12.2021 - 00:17Uhr
 */

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CustomItem {

    //TODO add a custom item api :D
    private final ItemStack itemStack;
    private final Map<ClickType, Consumer<Player>> actions;

    public CustomItem(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.actions = new HashMap<>();
    }

    public void leftClick(Consumer<Player> consumer) {
        actions.put(ClickType.LEFT_CLICK, consumer);
    }
    public void rightClick(Consumer<Player> consumer) {
        actions.put(ClickType.RIGHT_CLICK, consumer);
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
