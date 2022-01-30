package de.niklas.nikapi.spigot.item.custom.mob;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 30.01.2022 - 14:00Uhr
 */

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class CustomMob {

    private final String name;
    private final boolean customNameVisibility;
    private final double maxHealth;
    private final EntityType entityType;
    private final ItemStack mainHandItem;
    private final ItemStack[] armor;
    private final List<LootItem> lootTable;

    public CustomMob(String name, boolean customNameVisibility, double maxHealth, EntityType entityType, ItemStack mainHandItem, ItemStack[] armor, LootItem... lootItems) {
        this.name = name;
        this.customNameVisibility = customNameVisibility;
        this.maxHealth = maxHealth;
        this.entityType = entityType;
        this.mainHandItem = mainHandItem;
        this.armor = armor;
        this.lootTable = Arrays.asList(lootItems);
    }

    public void spawn(Location location) {
        LivingEntity livingEntity = (LivingEntity) location.getWorld().spawnEntity(location, entityType);
        livingEntity.setCustomNameVisible(customNameVisibility);
        livingEntity.setCustomName(name);
        livingEntity.setMaxHealth(maxHealth);
        livingEntity.setHealth(maxHealth);
        EntityEquipment entityEquipment = livingEntity.getEquipment();
        if(armor != null) entityEquipment.setArmorContents(armor);
        if(mainHandItem != null) entityEquipment.setItemInHand(mainHandItem);
        entityEquipment.setItemInHandDropChance(0);
        entityEquipment.setHelmetDropChance(0);
        entityEquipment.setChestplateDropChance(0);
        entityEquipment.setLeggingsDropChance(0);
        entityEquipment.setBootsDropChance(0);
        MobManager.getInstance().getCustomMobs().put(livingEntity, this);
    }
    public void tryDropLoot(Location location) {
        for(LootItem item : lootTable) {
            item.tryDropItem(location);
        }
    }

    public double getMaxHealth() {
        return maxHealth;
    }
}
