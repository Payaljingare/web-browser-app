package com.serhat.googlesearch.data.model;

import com.google.gson.annotations.SerializedName;

public class VideoAdditionalLink {
    @SerializedName("text")
    private String text;

    @SerializedName("href")
    private String href;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}