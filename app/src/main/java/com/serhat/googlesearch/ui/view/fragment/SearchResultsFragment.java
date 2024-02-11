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
import com.serhat.googlesearch.databinding.FragmentSearchResultsBinding;
import com.serhat.googlesearch.ui.view.activity.ResultsActivity;
import com.serhat.googlesearch.ui.view.adapter.SearchResultsAdapter;
import com.serhat.googlesearch.ui.viewmodel.SearchResultsViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SearchResultsFragment extends Fragment {
    private FragmentSearchResultsBinding binding;
    private SearchResultsViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_results, container, false);

        viewModel.searchResult.observe(getViewLifecycleOwner(), results -> {
            binding.setSearchResultsAdapter(new SearchResultsAdapter(requireContext(), results.getResults()));
        });

        String searchQuery = ((ResultsActivity) requireActivity()).searchQuery;
        viewModel.search(searchQuery);

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SearchResultsViewModel.class);
    }
}