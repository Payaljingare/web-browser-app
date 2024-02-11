package com.serhat.googlesearch.ui.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.serhat.googlesearch.R;
import com.serhat.googlesearch.databinding.ActivityMainBinding;
import com.serhat.googlesearch.helper.Constants;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private Context context;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = MainActivity.this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setMainActivity(this);
        binding.setAppTitle(getString(R.string.app_name));
        setSupportActionBar(binding.toolbarMain);
    }

    public void search(String q) {
        if (q.isEmpty()) {
            Toast.makeText(context, getString(R.string.msg_search_query_empty), Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(context, ResultsActivity.class);
            intent.putExtra(Constants.INTENT_EXTRA_SEARCH_QUERY, q);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.actionSearchHistory) {
            startActivity(new Intent(context, HistoryActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        binding.edtSearch.setText("");
        super.onResume();
    }
}