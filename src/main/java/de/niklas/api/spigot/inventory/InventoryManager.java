package de.niklas.api.spigot.inventory;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 23.12.2021 - 15:26Uhr
 */

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager {

    private static InventoryManager instance;

    private final Map<Player, InventoryMenu> openedMenus;

    public InventoryManager() {
        instance = this;
        openedMenus = new HashMap<>();
    }

    public Map<Player, InventoryMenu> getOpenedMenus() {
        return openedMenus;
    }

    public static InventoryManager getInstance() {
        return instance;
    }
}
