package de.niklas.nikapi.spigot.item.custom.mob;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 30.01.2022 - 14:07Uhr
 */

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class LootItem {

    //MobDrop
    private ItemStack itemStack;
    private int min = 1, max = 1;
    private double dropRate;
    private Random randomizer;

    public LootItem(ItemStack itemStack, double dropRate) {
        this.itemStack = itemStack;
        this.dropRate = dropRate;
        this.randomizer = new Random();
    }
    public LootItem(ItemStack itemStack, int min, int max, double dropRate) {
        this.itemStack = itemStack;
        this.min = min;
        this.max = max;
        this.dropRate = dropRate;
        this.randomizer = new Random();
    }

    public void tryDropItem(Location location) {
        if(Math.random() * 101 > dropRate) return;
        int amount = randomizer.nextInt(max - min + 1) + min;
        if(amount == 0) return;
        ItemStack item = itemStack.clone();
        item.setAmount(amount);
        location.getWorld().dropItemNaturally(location, item);
    }
}
