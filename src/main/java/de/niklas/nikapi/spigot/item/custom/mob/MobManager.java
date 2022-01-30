package de.niklas.nikapi.spigot.item.custom.mob;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 30.01.2022 - 14:43Uhr
 */

import org.bukkit.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class MobManager {

    private static MobManager instance;

    private final Map<Entity, CustomMob> mobs;

    public MobManager() {
        instance = this;
        mobs = new HashMap<>();
    }

    public Map<Entity, CustomMob> getCustomMobs() {
        return mobs;
    }

    public static MobManager getInstance() {
        return instance;
    }
}
