package de.niklas.nikapi.spigot.inventory;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 26.01.2022 - 15:05Uhr
 */

import org.bukkit.entity.Player;

public class InventoryMenuAction {

    private final Player player;
    private final boolean isLeftClick;
    private final boolean isRightClick;
    private final boolean isShiftClick;
    private final boolean isMiddleMouseClick;

    public InventoryMenuAction(Player player, boolean isLeftClick, boolean isRightClick, boolean isShiftClick, boolean isMiddleMouseClick) {
        this.player = player;
        this.isLeftClick = isLeftClick;
        this.isRightClick = isRightClick;
        this.isShiftClick = isShiftClick;
        this.isMiddleMouseClick = isMiddleMouseClick;
    }

    public Player getPlayer() {
        return player;
    }
    public boolean isLeftClick() {
        return isLeftClick;
    }
    public boolean isRightClick() {
        return isRightClick;
    }
    public boolean isShiftClick() {
        return isShiftClick;
    }
    public boolean isMiddleMouseClick() {
        return isMiddleMouseClick;
    }
}
