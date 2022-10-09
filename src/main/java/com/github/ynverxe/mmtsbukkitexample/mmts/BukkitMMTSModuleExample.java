package com.github.ynverxe.mmtsbukkitexample.mmts;

import com.github.ynverxe.mmts.bukkit.BukkitMMTSModule;
import com.github.ynverxe.mmtsbukkitexample.model.Title;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class BukkitMMTSModuleExample extends BukkitMMTSModule {

    @Override
    public void configure() {
        super.configure();

        getEntityContainer(Player.class, false)
                .bindSender(new PlayerMessageSender())
                .bindLinguist(new PlayerMetadataLinguist());

        bindMessageTypeAlias("title", Title.class);
        installExpansion(Title.class, new TitleMessageExpansion());
        addFormattingVisitor(String.class, (s, formattingContext) -> ChatColor.translateAlternateColorCodes('&', s));
    }
}