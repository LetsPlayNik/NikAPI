package de.niklas.nikapi.spigot.inventory;

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

    private ItemStack itemStack;
    private ItemMeta itemMeta;

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
    public ItemBuilder addEnchantment(Enchantment enchantment, int level, boolean ignoreLevelLimit) {
        itemMeta.addEnchant(enchantment, level, ignoreLevelLimit);
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
    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public String toString() {
        return "ItemBuilder{" + "itemMeta=" + itemMeta + ", itemStack=" + itemStack + "}";
    }
}
