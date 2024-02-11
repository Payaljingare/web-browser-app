package com.serhat.googlesearch.data.model;

import com.google.gson.annotations.SerializedName;

public class ImageDetails {
    @SerializedName("src")
    private String src;

    @SerializedName("alt")
    private String alt;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }
}