package de.niklas.nikapi.spigot.inventory;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 23.12.2021 - 15:26Uhr
 */

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InventoryManager {

    private static InventoryManager instance;

    private final Map<UUID, InventoryMenu> openedMenus;

    public InventoryManager() {
        instance = this;
        openedMenus = new HashMap<>();
    }

    public Map<UUID, InventoryMenu> getOpenedMenus() {
        return openedMenus;
    }

    public static InventoryManager getInstance() {
        return instance;
    }
}
