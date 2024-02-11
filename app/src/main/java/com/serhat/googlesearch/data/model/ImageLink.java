package com.serhat.googlesearch.data.model;

import com.google.gson.annotations.SerializedName;

public class ImageLink {
    @SerializedName("href")
    private String href;

    @SerializedName("title")
    private String title;

    @SerializedName("domain")
    private String domain;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}