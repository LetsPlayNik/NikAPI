package de.niklas.nikapi.spigot.listeners;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 30.01.2022 - 14:52Uhr
 */

import de.niklas.nikapi.spigot.item.custom.mob.MobManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathListener implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if(!MobManager.getInstance().getCustomMobs().containsKey(event.getEntity())) return;
        event.getDrops().clear();
        MobManager.getInstance().getCustomMobs().remove(event.getEntity()).tryDropLoot(event.getEntity().getLocation());
    }
}
