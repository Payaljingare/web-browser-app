package com.serhat.googlesearch.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "search_history")
public class SearchHistoryRow {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "search_id")
    @NonNull
    private int search_id;

    @ColumnInfo(name = "search_content")
    private String search_content;

    @ColumnInfo(name = "search_time")
    private String search_time;

    public SearchHistoryRow(int search_id, String search_content, String search_time) {
        this.search_id = search_id;
        this.search_content = search_content;
        this.search_time = search_time;
    }

    public int getSearch_id() {
        return search_id;
    }

    public void setSearch_id(int search_id) {
        this.search_id = search_id;
    }

    public String getSearch_content() {
        return search_content;
    }

    public void setSearch_content(String search_content) {
        this.search_content = search_content;
    }

    public String getSearch_time() {
        return search_time;
    }

    public void setSearch_time(String search_time) {
        this.search_time = search_time;
    }
}
