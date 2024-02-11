package com.serhat.googlesearch.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.serhat.googlesearch.data.model.SearchResult;
import com.serhat.googlesearch.data.repository.GoogleSearchRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SearchResultsViewModel extends ViewModel {
    private GoogleSearchRepository gRepo;
    public MutableLiveData<SearchResult> searchResult;

    @Inject
    public SearchResultsViewModel(GoogleSearchRepository gRepo) {
        this.gRepo = gRepo;
        searchResult = gRepo.getSearchResult();
    }

    public void search(String q) {
        gRepo.search(q);
    }
}
