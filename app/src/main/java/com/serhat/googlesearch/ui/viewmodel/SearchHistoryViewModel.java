package com.serhat.googlesearch.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.serhat.googlesearch.data.model.SearchHistoryRow;
import com.serhat.googlesearch.data.repository.GoogleSearchRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SearchHistoryViewModel extends ViewModel {
    private GoogleSearchRepository gRepo;
    public MutableLiveData<List<SearchHistoryRow>> searchHistory;
    public MutableLiveData<String> searchHistoryToastObserver;

    @Inject
    public SearchHistoryViewModel(GoogleSearchRepository gRepo) {
        this.gRepo = gRepo;
        searchHistory = gRepo.getSearchHistory();
        searchHistoryToastObserver = gRepo.getSearchHistoryToastObserver();
    }

    public void loadSearchHistory() {
        gRepo.loadSearchHistory();
    }

    public void filterSearchHistory(String search_content) {
        gRepo.filterSearchHistory(search_content);
    }

    public void deleteSearchHistory() {
        gRepo.deleteSearchHistory();
    }

    public void deleteSearchHistoryRow(int search_id) {
        gRepo.deleteSearchHistoryRow(search_id);
    }
}
