package com.serhat.googlesearch.ui.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.serhat.googlesearch.R;
import com.serhat.googlesearch.data.model.ImageResultRow;
import com.serhat.googlesearch.databinding.CardImageResultBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageResultsAdapter extends RecyclerView.Adapter<ImageResultsAdapter.ImageResultsViewHolder> {
    private Context mContext;
    private List<ImageResultRow> imageResults;

    public ImageResultsAdapter(Context mContext, List<ImageResultRow> imageResults) {
        this.mContext = mContext;
        this.imageResults = imageResults;
    }

    public class ImageResultsViewHolder extends RecyclerView.ViewHolder {
        public CardImageResultBinding binding;

        public ImageResultsViewHolder(@NonNull CardImageResultBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public ImageResultsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardImageResultBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.card_image_result, parent, false);
        return new ImageResultsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageResultsViewHolder holder, int position) {
        ImageResultRow row = imageResults.get(position);

        holder.binding.setImageResultsAdapter(ImageResultsAdapter.this);
        holder.binding.setImageResultRow(row);

        Picasso.get().load(row.getImageDetails().getSrc()).into(holder.binding.imageResultImg);
        Log.e("img link", row.getImageDetails().getSrc());
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
        return imageResults.size();
    }
}
