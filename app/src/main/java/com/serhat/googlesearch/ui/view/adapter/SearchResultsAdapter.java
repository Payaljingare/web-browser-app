package com.serhat.googlesearch.ui.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.serhat.googlesearch.R;
import com.serhat.googlesearch.data.model.SearchResultRow;
import com.serhat.googlesearch.databinding.CardSearchResultBinding;

import java.util.List;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.SearchResultsViewHolder> {
    private Context mContext;
    private List<SearchResultRow> searchResults;

    public SearchResultsAdapter(Context mContext, List<SearchResultRow> searchResults) {
        this.mContext = mContext;
        this.searchResults = searchResults;
    }

    public class SearchResultsViewHolder extends RecyclerView.ViewHolder {
        public CardSearchResultBinding binding;

        public SearchResultsViewHolder(@NonNull CardSearchResultBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public SearchResultsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardSearchResultBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.card_search_result, parent, false);
        return new SearchResultsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultsViewHolder holder, int position) {
        SearchResultRow row = searchResults.get(position);

        holder.binding.setSearchResultsAdapter(SearchResultsAdapter.this);
        holder.binding.setSearchResultRow(row);
    }

    public void openPage(String link) {
        if (link.trim().isEmpty()) {
            Toast.makeText(mContext, mContext.getResources().getString(R.string.msg_link_not_working), Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            mContext.startActivity(intent);
        }
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }
}
