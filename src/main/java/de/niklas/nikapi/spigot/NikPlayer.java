package de.niklas.nikapi.spigot;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 14.02.2022 - 16:22Uhr
 */

import de.niklas.nikapi.spigot.nms.MinecraftVersion;
import io.netty.channel.*;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Consumer;

public class NikPlayer {

    private final Player player;

    public NikPlayer(Player player) {
        this.player = player;
    }

    public void injectPacketListener(Consumer<Object> incomingPacket, Consumer<Object> outgoingPacket) {
        ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object object) throws Exception {
                incomingPacket.accept(object);
                super.channelRead(ctx, object);
            }
            @Override
            public void write(ChannelHandlerContext ctx, Object object, ChannelPromise promise) throws Exception {
                outgoingPacket.accept(object);
                super.write(ctx, object, promise);
            }
        };
        try {
            //Object handle = player.getClass().getMethod("getHandle").invoke(player);
            //Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            Object playerConnection = getPlayerConnection();
            Object networkManager;
            Object channelField;
            try {
                networkManager = playerConnection.getClass().getField("networkManager").get(playerConnection);
                channelField = networkManager.getClass().getField("channel").get(networkManager);
            } catch(NoSuchFieldException exception) {
                networkManager = playerConnection.getClass().getField("a").get(playerConnection);
                channelField = networkManager.getClass().getField("k").get(networkManager);
            }
            //Object networkManager = playerConnection.getClass().getField("networkManager").get(playerConnection);
            //Object channel = networkManager.getClass().getField("channel").get(networkManager);
            Channel channel = (Channel) channelField;
            ChannelPipeline pipeline = channel.pipeline();
            /*Method pipelineMethod = channel.getClass().getDeclaredMethod("pipeline");
            pipelineMethod.setAccessible(true);
            Object pipeline = pipelineMethod.invoke(channel);*/
            ChannelPipeline channelPipeline = (ChannelPipeline) pipeline;
            channelPipeline.addBefore("packet_handler", player.getName(), channelDuplexHandler);
            channelPipeline.addAfter("decoder", "PacketInjector", new MessageToMessageDecoder<Object>() {
                @Override
                protected void decode(ChannelHandlerContext channelHandlerContext, Object object, List<Object> list) throws Exception {
                    list.add(object);
                    incomingPacket.accept(object);
                }
            });
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }
    /*public void uninject() {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            Object networkManager = playerConnection.getClass().getField("networkManager").get(playerConnection);
            Object channelField = networkManager.getClass().getField("channel").get(networkManager);
            Channel channel = (Channel) channelField;
            channel.eventLoop().submit(() -> {
                channel.pipeline().remove(player.getName());
                return null;
            });
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }*/
    public void sendPacket(Object packet) {
        try {
            //Object handle = player.getClass().getMethod("getHandle").invoke(player);
            //Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            Object playerConnection = getPlayerConnection();
            Method sendPacket = playerConnection.getClass().getMethod("sendPacket", Class.forName("net.minecraft.server." + MinecraftVersion.getVersion() + ".Packet"));
            sendPacket.invoke(playerConnection, packet);
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    public Object getPlayerConnection() {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            return handle.getClass().getField("playerConnection").get(handle);
        } catch(Exception exception) {
            if(exception instanceof NoSuchFieldException) {
                try {
                    Object handle = player.getClass().getMethod("getHandle").invoke(player);
                    return handle.getClass().getField("b").get(handle);
                } catch(Exception ignored) {}
            }
        }
        return null;
    }
}
