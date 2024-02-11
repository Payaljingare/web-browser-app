package com.serhat.googlesearch.ui.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.serhat.googlesearch.R;
import com.serhat.googlesearch.databinding.ActivityHistoryBinding;
import com.serhat.googlesearch.ui.view.adapter.SearchHistoryAdapter;
import com.serhat.googlesearch.ui.viewmodel.SearchHistoryViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HistoryActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private Context context;
    private ActivityHistoryBinding binding;
    private SearchHistoryViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = HistoryActivity.this;
        viewModel = new ViewModelProvider(this).get(SearchHistoryViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_history);

        binding.setAppTitle(getString(R.string.title_search_history));
        binding.setHistoryActivity(this);
        setSupportActionBar(binding.toolbarHistory);

        viewModel.searchHistoryToastObserver.observe(this, toastMessage -> {
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
        });

        viewModel.searchHistory.observe(this, searchHistory -> {
            binding.setSearchHistoryAdapter(new SearchHistoryAdapter(context, searchHistory, viewModel));
        });

        viewModel.loadSearchHistory();
    }

    public void deleteSearchHistory(View view) {
        Snackbar.make(view, getString(R.string.msg_are_you_sure_to_delete_all_search_history), Snackbar.LENGTH_INDEFINITE).setAction(getString(R.string.btn_yes), v -> {
            if (viewModel.searchHistory.getValue().size() == 0) {
                Toast.makeText(context, getString(R.string.msg_there_is_nothing_to_delete), Toast.LENGTH_SHORT).show();
            } else {
                viewModel.deleteSearchHistory();
            }
        }).show();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        viewModel.filterSearchHistory(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        viewModel.filterSearchHistory(newText);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_history, menu);

        MenuItem item = menu.findItem(R.id.actionSearch);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}