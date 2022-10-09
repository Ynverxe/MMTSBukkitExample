package com.github.ynverxe.mmtsbukkitexample.mmts;

import com.github.ynverxe.mmts.bukkit.SpigotLinguist;
import com.github.ynverxe.mmts.translation.Linguist;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PlayerMetadataLinguist implements Linguist<Player> {

    private final Linguist<Player> delegate = new SpigotLinguist();

    @Override
    public @Nullable String resolveLanguage(Player player) {
        List<MetadataValue> metadataValues = player.getMetadata("language");

        return metadataValues.isEmpty() ? delegate.resolveLanguage(player) : metadataValues.get(0).asString();
    }
}