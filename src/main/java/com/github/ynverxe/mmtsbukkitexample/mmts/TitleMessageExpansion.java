package com.github.ynverxe.mmtsbukkitexample.mmts;

import com.github.ynverxe.data.DataNode;
import com.github.ynverxe.mmts.core.format.FormattingContext;
import com.github.ynverxe.mmts.core.format.MessageExpansion;
import com.github.ynverxe.mmts.core.format.MessageFormatter;
import com.github.ynverxe.mmts.translation.TranslationData;
import com.github.ynverxe.mmtsbukkitexample.model.Title;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TitleMessageExpansion implements MessageExpansion<Title> {
    @Override
    public @NotNull Title createNewMessage(
            @NotNull TranslationData translationData,
            @NotNull MessageFormatter messageFormatter,
            @NotNull FormattingContext formattingContext
    ) {
        FormattingContext.Configurator configurator = formattingContext.toConfigurator();

        String title = translationData.getString("title");
        String subTitle = translationData.getString("subTitle");
        int fadeIn = translationData.getInt("fadeIn");
        int stay = translationData.getInt("stay");
        int fadeOut = translationData.getInt("fadeOut");

        return new Title(messageFormatter.formatString(title, configurator), messageFormatter.formatString(subTitle, configurator), fadeIn, stay, fadeOut);
    }

    @Override
    public @Nullable TranslationData dismountAsData(@NotNull Title title) {
        return TranslationData.withoutPath(DataNode.fromMap(title.toMap()));
    }
}