package com.serhat.googlesearch.data.model;

import com.google.gson.annotations.SerializedName;

public class VideoCite {
    @SerializedName("domain")
    private String domain;

    @SerializedName("span")
    private String span;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSpan() {
        return span;
    }

    public void setSpan(String span) {
        this.span = span;
    }
}