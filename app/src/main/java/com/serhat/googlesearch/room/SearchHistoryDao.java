package com.serhat.googlesearch.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.serhat.googlesearch.data.model.SearchHistoryRow;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface SearchHistoryDao {
    @Query("SELECT * FROM search_history ORDER BY search_id DESC")
    Single<List<SearchHistoryRow>> getSearchHistory();

    @Query("SELECT * FROM search_history WHERE search_content LIKE '%' || :search_content ||'%' ORDER BY search_id DESC")
    Single<List<SearchHistoryRow>> filterSearchHistory(String search_content);

    @Query("DELETE FROM search_history")
    Completable deleteSearchHistory();

    @Insert
    Completable addSearchHistoryRow(SearchHistoryRow searchHistoryRow);

    @Delete
    Completable deleteSearchHistoryRow(SearchHistoryRow searchHistoryRow);
}
