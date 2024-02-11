package com.serhat.googlesearch.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.serhat.googlesearch.data.model.VideoResult;
import com.serhat.googlesearch.data.repository.GoogleSearchRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class VideoResultsViewModel extends ViewModel {
    private GoogleSearchRepository gRepo;
    public MutableLiveData<VideoResult> videoResult;

    @Inject
    public VideoResultsViewModel(GoogleSearchRepository gRepo) {
        this.gRepo = gRepo;
        videoResult = gRepo.getVideoResult();
    }

    public void videoSearch(String q) {
        gRepo.videoSearch(q);
    }
}
