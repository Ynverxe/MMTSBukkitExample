package com.github.ynverxe.mmtsbukkitexample.model;

import java.util.HashMap;
import java.util.Map;

public final class Title {

    private final String title;
    private final String subTitle;
    private final int fadeIn, stay, fadeOut;

    public Title(String title, String subTitle, int fadeIn, int stay, int fadeOut) {
        this.title = title != null ? title : "";
        this.subTitle = subTitle != null ? subTitle : "";
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public int getFadeIn() {
        return fadeIn;
    }

    public int getStay() {
        return stay;
    }

    public int getFadeOut() {
        return fadeOut;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> objectMap = new HashMap<>();

        objectMap.put("title", title);
        objectMap.put("subTitle", subTitle);
        objectMap.put("fadeIn", fadeIn);
        objectMap.put("stay", stay);
        objectMap.put("fadeOut", fadeOut);

        return objectMap;
    }
}