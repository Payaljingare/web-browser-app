package com.serhat.googlesearch.ui.view.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.serhat.googlesearch.R;
import com.serhat.googlesearch.databinding.FragmentVideoResultsBinding;
import com.serhat.googlesearch.ui.view.activity.ResultsActivity;
import com.serhat.googlesearch.ui.view.adapter.VideoResultsAdapter;
import com.serhat.googlesearch.ui.viewmodel.VideoResultsViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class VideoResultsFragment extends Fragment {
    private FragmentVideoResultsBinding binding;
    private VideoResultsViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_video_results, container, false);

        viewModel.videoResult.observe(getViewLifecycleOwner(), results -> {
            binding.setVideoResultsAdapter(new VideoResultsAdapter(requireContext(), results.getResults()));
        });

        String searchQuery = ((ResultsActivity) requireActivity()).searchQuery;
        viewModel.videoSearch(searchQuery);

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(VideoResultsViewModel.class);
    }
}