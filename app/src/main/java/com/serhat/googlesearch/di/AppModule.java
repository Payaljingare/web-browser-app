package com.serhat.googlesearch.di;

import android.content.Context;

import androidx.room.Room;

import com.serhat.googlesearch.api.ApiInterface;
import com.serhat.googlesearch.api.ApiUtils;
import com.serhat.googlesearch.data.repository.GoogleSearchRepository;
import com.serhat.googlesearch.room.RoomDB;
import com.serhat.googlesearch.room.SearchHistoryDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public GoogleSearchRepository provideGoogleSearchRepository(ApiInterface apiService, SearchHistoryDao searchHistoryDao) {
        return new GoogleSearchRepository(apiService, searchHistoryDao);
    }

    @Provides
    @Singleton
    public ApiInterface provideApiInterface() {
        return ApiUtils.getApiInterface();
    }

    @Provides
    @Singleton
    public SearchHistoryDao provideSearchHistoryDao(@ApplicationContext Context context) {
        RoomDB db = Room.databaseBuilder(context, RoomDB.class, "google_search.sqlite")
                .createFromAsset("google_search.sqlite")
                .build();

        return db.getSearchHistoryDao();
    }
}
