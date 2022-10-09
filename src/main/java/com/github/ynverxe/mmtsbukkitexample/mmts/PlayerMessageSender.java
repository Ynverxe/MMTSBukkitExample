package com.github.ynverxe.mmtsbukkitexample.mmts;

import com.github.ynverxe.mmts.core.entity.MessageSender;
import com.github.ynverxe.mmtsbukkitexample.model.Title;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftChatMessage;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PlayerMessageSender implements MessageSender<Player> {
    @Override
    public void sendMessage(@NotNull Player player, @NotNull String mode, @NotNull Object message) {
        if (message instanceof Title) {
            Title title = (Title) message;
            sendTitle(player, title);
        } else {
            String text = Objects.toString(message);

            if ("action-bar".equals(mode)) {
                sendActionBar(player, text);
            } else {
                player.sendMessage(text);
            }
        }
    }

    @Override
    public boolean offer(@NotNull Class<?> aClass) {
        return true;
    }

    private void sendTitle(Player player, Title titleModel) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        PlayerConnection playerConnection = craftPlayer.getHandle().playerConnection;

        PacketPlayOutTitle times = new PacketPlayOutTitle(titleModel.getFadeIn(), titleModel.getStay(), titleModel.getFadeOut());
        playerConnection.sendPacket(times);

        String title = titleModel.getTitle();

        if (title != null) {
            PacketPlayOutTitle packetTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, CraftChatMessage.fromString(title)[0]);
            playerConnection.sendPacket(packetTitle);
        }

        String subtitle = titleModel.getSubTitle();
        if (subtitle != null) {
            PacketPlayOutTitle packetSubtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, CraftChatMessage.fromString(subtitle)[0]);
            playerConnection.sendPacket(packetSubtitle);
        }
    }

    private void sendActionBar(Player player, String text) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        PlayerConnection playerConnection = craftPlayer.getHandle().playerConnection;

        IChatBaseComponent component = new ChatMessage(text);
        playerConnection.sendPacket(new PacketPlayOutChat(component, (byte) 2));
    }
}