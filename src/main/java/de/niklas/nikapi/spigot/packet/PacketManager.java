package de.niklas.nikapi.spigot.packet;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 14.02.2022 - 16:21Uhr
 */

public class PacketManager {

    private static PacketManager instance;

    public PacketManager() {
        instance = this;
    }

    public static PacketManager getInstance() {
        return instance;
    }
}
