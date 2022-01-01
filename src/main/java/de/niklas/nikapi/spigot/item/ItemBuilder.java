package de.niklas.nikapi.spigot.item;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 30.12.2021 - 23:45Uhr
 */

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemBuilder {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    public ItemBuilder(Material material) {
        itemStack = new ItemStack(material);
        itemMeta = itemStack.getItemMeta();
    }
    public ItemBuilder(Material material, int amount) {
        itemStack = new ItemStack(material, amount);
        itemMeta = itemStack.getItemMeta();
    }
    public ItemBuilder(Material material, int amount, int damage) {
        itemStack = new ItemStack(material, amount, (short) damage);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder setDisplayName(String displayName){
        itemMeta.setDisplayName(displayName);
        return this;
    }
    public ItemBuilder setLore(String... lore){
        itemMeta.setLore(Arrays.asList(lore));
        return this;
    }
    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        itemStack.addEnchantment(enchantment, level);
        return this;
    }
    public ItemBuilder addUnsafeEnchantment(Enchantment enchantment, int level) {
        itemMeta.addEnchant(enchantment, level, true);
        return this;
    }
    public ItemBuilder addItemFlags(ItemFlag... flags){
        itemMeta.addItemFlags(flags);
        return this;
    }
    public ItemBuilder setDurability(short durability) {
        itemStack.setDurability(durability);
        return this;
    }
    public ItemBuilder setUnbreakable(boolean unbreakable) {
        itemMeta.spigot().setUnbreakable(unbreakable);
        return this;
    }
    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
    public ItemMeta getItemMeta() {
        return itemMeta;
    }
}
