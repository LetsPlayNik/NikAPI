package de.niklas.nikapi;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 10.02.2022 - 17:50Uhr
 */

import de.niklas.nikapi.spigot.nms.MinecraftVersion;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HologramBuilder {

    private final Location location;
    private final String text;


    public HologramBuilder(Location location, String text) {
        this.location = location;
        this.text = text;
    }

    public void spawn() {

    }

    private Method sendPacket = null;

    private Object getHandle(Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return object.getClass().getMethod("getHandle").invoke(object);
    }
    private void sendPacket(Player player, Object packet) {
        try {
            Object handle = getHandle(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            if(sendPacket == null) {
                sendPacket = playerConnection.getClass().getMethod("sendPacket", Class.forName("net.minecraft.server." + MinecraftVersion.getVersion() + ".Packet"));
            }
            sendPacket.invoke(playerConnection, packet);
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }
}
