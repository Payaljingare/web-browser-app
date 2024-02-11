package com.serhat.googlesearch.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.serhat.googlesearch.data.model.SearchHistoryRow;

@Database(entities = {SearchHistoryRow.class}, version = 1)
public abstract class RoomDB extends RoomDatabase {
    public abstract SearchHistoryDao getSearchHistoryDao();
}
