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
import com.serhat.googlesearch.data.model.VideoResultRow;
import com.serhat.googlesearch.databinding.CardVideoResultBinding;

import java.util.List;

public class VideoResultsAdapter extends RecyclerView.Adapter<VideoResultsAdapter.VideoResultsViewHolder> {
    private Context mContext;
    private List<VideoResultRow> videoResults;

    public VideoResultsAdapter(Context mContext, List<VideoResultRow> videoResults) {
        this.mContext = mContext;
        this.videoResults = videoResults;
    }

    public class VideoResultsViewHolder extends RecyclerView.ViewHolder {
        public CardVideoResultBinding binding;

        public VideoResultsViewHolder(@NonNull CardVideoResultBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public VideoResultsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardVideoResultBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.card_video_result, parent, false);
        return new VideoResultsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoResultsViewHolder holder, int position) {
        VideoResultRow row = videoResults.get(position);

        holder.binding.setVideoResultsAdapter(VideoResultsAdapter.this);
        holder.binding.setVideoResultRow(row);
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
        return videoResults.size();
    }
}
