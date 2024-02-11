package com.serhat.googlesearch.data.model;

import com.google.gson.annotations.SerializedName;

public class SearchResultRow {
    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("link")
    private String link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}