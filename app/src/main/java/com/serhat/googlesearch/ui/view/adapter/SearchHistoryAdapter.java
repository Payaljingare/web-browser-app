package com.serhat.googlesearch.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.serhat.googlesearch.R;
import com.serhat.googlesearch.data.model.SearchHistoryRow;
import com.serhat.googlesearch.databinding.CardSearchHistoryRowBinding;
import com.serhat.googlesearch.ui.viewmodel.SearchHistoryViewModel;

import java.util.List;

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryViewHolder> {
    private Context mContext;
    private List<SearchHistoryRow> searchHistory;
    private SearchHistoryViewModel viewModel;

    public SearchHistoryAdapter(Context mContext, List<SearchHistoryRow> searchHistory, SearchHistoryViewModel viewModel) {
        this.mContext = mContext;
        this.searchHistory = searchHistory;
        this.viewModel = viewModel;
    }

    public class SearchHistoryViewHolder extends RecyclerView.ViewHolder {
        public CardSearchHistoryRowBinding binding;

        public SearchHistoryViewHolder(@NonNull CardSearchHistoryRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public SearchHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardSearchHistoryRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.card_search_history_row, parent, false);
        return new SearchHistoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHistoryViewHolder holder, int position) {
        SearchHistoryRow row = searchHistory.get(position);

        holder.binding.setSearchHistoryAdapter(SearchHistoryAdapter.this);
        holder.binding.setSearchHistoryRow(row);
    }

    public void deleteRow(int search_id) {
        viewModel.deleteSearchHistoryRow(search_id);
    }

    @Override
    public int getItemCount() {
        return searchHistory.size();
    }
}
