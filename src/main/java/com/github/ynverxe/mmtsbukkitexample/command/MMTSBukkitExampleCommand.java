package com.github.ynverxe.mmtsbukkitexample.command;

import com.github.ynverxe.mmts.core.MMTSHandler;
import com.github.ynverxe.mmtsbukkitexample.MMTSBukkitExamplePlugin;
import com.github.ynverxe.mmtsbukkitexample.model.Title;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Arrays;
import java.util.List;

@Command(names = "message")
public class MMTSBukkitExampleCommand implements CommandClass {

    private static final List<String> AVAILABLE_LANGUAGES = Arrays.asList("es", "en");
    private final MMTSHandler mmtsHandler;

    public MMTSBukkitExampleCommand(MMTSHandler mmtsHandler) {
        this.mmtsHandler = mmtsHandler;
    }

    @Command(names = "text")
    public boolean sendTextMessage(@Sender Player player) {
        mmtsHandler.translating("text-message", String.class).send(player);
        return true;
    }

    @Command(names = "title")
    public boolean sendTitleMessage(@Sender Player player) {
        mmtsHandler.translating("title-message", Title.class).send(player);
        return true;
    }

    @Command(names = "action-bar")
    public boolean sendActionBarMessage(@Sender Player player) {
        mmtsHandler.translating("action-bar-message", String.class)
                .send(player, "action-bar");
        return true;
    }

    @Command(names = "lang")
    public boolean changeLang(@Sender Player player, String lang) {
        if (!AVAILABLE_LANGUAGES.contains(lang)) {
            player.sendMessage(ChatColor.RED + "Invalid lang: " + lang);
            player.sendMessage(ChatColor.GREEN + "Available languages: " + AVAILABLE_LANGUAGES);
            return true;
        }

        player.setMetadata("language", new FixedMetadataValue(MMTSBukkitExamplePlugin.getInstance(), lang));
        player.sendMessage("&eLanguage changed correctly: " + lang);

        return true;
    }
}