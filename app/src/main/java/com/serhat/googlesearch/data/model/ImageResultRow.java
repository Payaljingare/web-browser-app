package com.serhat.googlesearch.data.model;

import com.google.gson.annotations.SerializedName;

public class ImageResultRow {
    @SerializedName("image")
    private ImageDetails imageDetails;

    @SerializedName("link")
    private ImageLink imageLink;

    public ImageDetails getImageDetails() {
        return imageDetails;
    }

    public void setImageDetails(ImageDetails imageDetails) {
        this.imageDetails = imageDetails;
    }

    public ImageLink getImageLink() {
        return imageLink;
    }

    public void setImageLink(ImageLink imageLink) {
        this.imageLink = imageLink;
    }
}