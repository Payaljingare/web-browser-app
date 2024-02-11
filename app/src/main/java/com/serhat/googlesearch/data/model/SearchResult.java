package com.serhat.googlesearch.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResult {
    @SerializedName("results")
    private List<SearchResultRow> results = null;

    @SerializedName("image_results")
    private List<Object> imageResults = null;

    @SerializedName("total")
    private Object total;

    @SerializedName("answers")
    private List<String> answers = null;

    @SerializedName("ts")
    private Double ts;

    @SerializedName("device_region")
    private String deviceRegion;

    @SerializedName("device_type")
    private String deviceType;

    public List<SearchResultRow> getResults() {
        return results;
    }

    public void setResults(List<SearchResultRow> results) {
        this.results = results;
    }

    public List<Object> getImageResults() {
        return imageResults;
    }

    public void setImageResults(List<Object> imageResults) {
        this.imageResults = imageResults;
    }

    public Object getTotal() {
        return total;
    }

    public void setTotal(Object total) {
        this.total = total;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public Double getTs() {
        return ts;
    }

    public void setTs(Double ts) {
        this.ts = ts;
    }

    public String getDeviceRegion() {
        return deviceRegion;
    }

    public void setDeviceRegion(String deviceRegion) {
        this.deviceRegion = deviceRegion;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}