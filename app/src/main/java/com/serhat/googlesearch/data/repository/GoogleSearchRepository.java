package com.serhat.googlesearch.data.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.serhat.googlesearch.api.ApiInterface;
import com.serhat.googlesearch.data.model.ImageResult;
import com.serhat.googlesearch.data.model.SearchHistoryRow;
import com.serhat.googlesearch.data.model.SearchResult;
import com.serhat.googlesearch.data.model.VideoResult;
import com.serhat.googlesearch.helper.Constants;
import com.serhat.googlesearch.room.SearchHistoryDao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoogleSearchRepository {
    private MutableLiveData<SearchResult> searchResult;
    private MutableLiveData<ImageResult> imageResult;
    private MutableLiveData<VideoResult> videoResult;

    private MutableLiveData<List<SearchHistoryRow>> searchHistory;
    private MutableLiveData<String> searchHistoryToastObserver;

    private ApiInterface apiService;
    private SearchHistoryDao searchHistoryDao;

    public GoogleSearchRepository(ApiInterface apiService, SearchHistoryDao searchHistoryDao) {
        this.apiService = apiService;
        this.searchHistoryDao = searchHistoryDao;

        searchResult = new MutableLiveData<>();
        imageResult = new MutableLiveData<>();
        videoResult = new MutableLiveData<>();

        searchHistory = new MutableLiveData<>();
        searchHistoryToastObserver = new MutableLiveData<>();
    }

    public void search(String q) {
        apiService.search(Constants.USER_AGENT, Constants.PROXY_LOCATION, Constants.API_KEY, Constants.API_HOST, q).enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                if (response.body() != null && response.isSuccessful()) {
                    searchResult.setValue(response.body());
                    addSearchHistoryRow(q);
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                Log.e("Search error", t.getMessage());
            }
        });
    }

    public void imageSearch(String q) {
        apiService.imageSearch(Constants.USER_AGENT, Constants.PROXY_LOCATION, Constants.API_KEY, Constants.API_HOST, q).enqueue(new Callback<ImageResult>() {
            @Override
            public void onResponse(Call<ImageResult> call, Response<ImageResult> response) {
                if (response.body() != null && response.isSuccessful()) {
                    imageResult.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ImageResult> call, Throwable t) {
                Log.e("Image search error", t.getMessage());
            }
        });
    }

    public void videoSearch(String q) {
        apiService.videoSearch(Constants.USER_AGENT, Constants.PROXY_LOCATION, Constants.API_KEY, Constants.API_HOST, q).enqueue(new Callback<VideoResult>() {
            @Override
            public void onResponse(Call<VideoResult> call, Response<VideoResult> response) {
                if (response.body() != null && response.isSuccessful()) {
                    videoResult.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<VideoResult> call, Throwable t) {
                Log.e("Video search error", t.getMessage());
            }
        });
    }

    public MutableLiveData<SearchResult> getSearchResult() {
        return searchResult;
    }

    public MutableLiveData<ImageResult> getImageResult() {
        return imageResult;
    }

    public MutableLiveData<VideoResult> getVideoResult() {
        return videoResult;
    }

    public MutableLiveData<List<SearchHistoryRow>> getSearchHistory() {
        return searchHistory;
    }

    public MutableLiveData<String> getSearchHistoryToastObserver() {
        return searchHistoryToastObserver;
    }

    public void loadSearchHistory() {
        searchHistoryDao.getSearchHistory().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<SearchHistoryRow>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<SearchHistoryRow> searchHistoryRows) {
                        searchHistory.setValue(searchHistoryRows);
                    }

                    @SuppressLint("LongLogTag")
                    @Override
                    public void onError(Throwable e) {
                        Log.e("Search history load error", e.getMessage());
                    }
                });
    }

    public void filterSearchHistory(String search_content) {
        searchHistoryDao.filterSearchHistory(search_content).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<SearchHistoryRow>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<SearchHistoryRow> searchHistoryRows) {
                        searchHistory.setValue(searchHistoryRows);
                    }

                    @SuppressLint("LongLogTag")
                    @Override
                    public void onError(Throwable e) {
                        Log.e("Search history filter error", e.getMessage());
                    }
                });
    }

    public void deleteSearchHistory() {
        searchHistoryDao.deleteSearchHistory().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        loadSearchHistory();
                        searchHistoryToastObserver.setValue("Search history deleted");
                    }

                    @SuppressLint("LongLogTag")
                    @Override
                    public void onError(Throwable e) {
                        Log.e("Search history delete error", e.getMessage());
                    }
                });
    }

    public void addSearchHistoryRow(String search_content) {
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("d MMMM, HH:mm", new Locale("en"));

        searchHistoryDao.addSearchHistoryRow(new SearchHistoryRow(0, search_content, df.format(new Date()))).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        loadSearchHistory();
                    }

                    @SuppressLint("LongLogTag")
                    @Override
                    public void onError(Throwable e) {
                        Log.e("Search history add row error", e.getMessage());
                    }
                });
    }

    public void deleteSearchHistoryRow(int search_id) {
        searchHistoryDao.deleteSearchHistoryRow(new SearchHistoryRow(search_id,"", "")).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        loadSearchHistory();
                        searchHistoryToastObserver.setValue("Search row deleted");
                    }

                    @SuppressLint("LongLogTag")
                    @Override
                    public void onError(Throwable e) {
                        Log.e("Search history delete row error", e.getMessage());
                    }
                });
    }
}
