package com.serhat.googlesearch.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoResultRow {
    @SerializedName("title")
    private String title;

    @SerializedName("link")
    private String link;

    @SerializedName("description")
    private String description;

    @SerializedName("additional_links")
    private List<VideoAdditionalLink> additionalLinks = null;

    @SerializedName("cite")
    private VideoCite videoCite;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<VideoAdditionalLink> getAdditionalLinks() {
        return additionalLinks;
    }

    public void setAdditionalLinks(List<VideoAdditionalLink> additionalLinks) {
        this.additionalLinks = additionalLinks;
    }

    public VideoCite getVideoCite() {
        return videoCite;
    }

    public void setVideoCite(VideoCite videoCite) {
        this.videoCite = videoCite;
    }
}