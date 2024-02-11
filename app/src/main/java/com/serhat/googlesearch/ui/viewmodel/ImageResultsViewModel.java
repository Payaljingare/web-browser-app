package com.serhat.googlesearch.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.serhat.googlesearch.data.model.ImageResult;
import com.serhat.googlesearch.data.repository.GoogleSearchRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ImageResultsViewModel extends ViewModel {
    private GoogleSearchRepository gRepo;
    public MutableLiveData<ImageResult> imageResult;

    @Inject
    public ImageResultsViewModel(GoogleSearchRepository gRepo) {
        this.gRepo = gRepo;
        imageResult = gRepo.getImageResult();
    }

    public void imageSearch(String q) {
        gRepo.imageSearch(q);
    }
}
