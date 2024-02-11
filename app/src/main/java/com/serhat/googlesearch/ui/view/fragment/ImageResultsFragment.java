package com.serhat.googlesearch.ui.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.serhat.googlesearch.R;
import com.serhat.googlesearch.databinding.FragmentImageResultsBinding;
import com.serhat.googlesearch.ui.view.activity.ResultsActivity;
import com.serhat.googlesearch.ui.view.adapter.ImageResultsAdapter;
import com.serhat.googlesearch.ui.viewmodel.ImageResultsViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ImageResultsFragment extends Fragment {
    private FragmentImageResultsBinding binding;
    private ImageResultsViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_image_results, container, false);

        viewModel.imageResult.observe(getViewLifecycleOwner(), results -> {
            binding.setImageResultsAdapter(new ImageResultsAdapter(requireContext(), results.getImageResults()));
        });

        String searchQuery = ((ResultsActivity) requireActivity()).searchQuery;
        viewModel.imageSearch(searchQuery);

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ImageResultsViewModel.class);
    }
}