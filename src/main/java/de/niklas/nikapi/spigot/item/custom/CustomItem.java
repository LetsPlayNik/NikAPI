package de.niklas.nikapi.spigot.item.custom;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 31.12.2021 - 00:17Uhr
 */

import org.bukkit.inventory.ItemStack;

public class CustomItem {

    //TODO add a custom item api :D
    private final ItemStack itemStack;

    public CustomItem(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
