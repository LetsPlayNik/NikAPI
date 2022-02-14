package de.niklas.nikapi.spigot;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 14.02.2022 - 16:22Uhr
 */

import de.niklas.nikapi.spigot.nms.MinecraftVersion;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;

public class NikPlayer {

    private final Player player;

    public NikPlayer(Player player) {
        this.player = player;
    }

    public void sendPacket(Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            Method sendPacket = playerConnection.getClass().getMethod("sendPacket", Class.forName("net.minecraft.server." + MinecraftVersion.getVersion() + ".Packet"));
            sendPacket.invoke(playerConnection, packet);
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }
}
