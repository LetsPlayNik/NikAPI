package de.niklas.nikapi.spigot.item.custom;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 31.12.2021 - 00:19Uhr
 */

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CustomItemManager {

    private static CustomItemManager instance;

    //TODO add custom item manager
    /*private final Map<Player, InventoryMenu> openedMenus;

    public CustomItemManager() {
        instance = this;
        openedMenus = new HashMap<>();
    }

    public Map<Player, InventoryMenu> getOpenedMenus() {
        return openedMenus;
    }*/

    public static CustomItemManager getInstance() {
        return instance;
    }
}
