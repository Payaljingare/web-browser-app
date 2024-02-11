package com.serhat.googlesearch.ui.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.serhat.googlesearch.R;
import com.serhat.googlesearch.databinding.ActivityResultsBinding;
import com.serhat.googlesearch.helper.Constants;
import com.serhat.googlesearch.ui.view.fragment.ImageResultsFragment;
import com.serhat.googlesearch.ui.view.fragment.SearchResultsFragment;
import com.serhat.googlesearch.ui.view.fragment.VideoResultsFragment;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ResultsActivity extends AppCompatActivity {
    private Context context;
    private ActivityResultsBinding binding;

    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private ArrayList<String> fragmentTitleList = new ArrayList<>();

    public String searchQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        searchQuery = getIntent().getStringExtra(Constants.INTENT_EXTRA_SEARCH_QUERY);

        context = ResultsActivity.this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_results);

        binding.setResultsActivity(this);

        binding.setAppTitle(getString(R.string.title_search_results) + " " + searchQuery);
        initViewPager();

        Toast.makeText(context, getString(R.string.msg_results_loading), Toast.LENGTH_LONG).show();
    }

    private void initViewPager() {
        fragmentList.add(new SearchResultsFragment());
        fragmentList.add(new ImageResultsFragment());
        fragmentList.add(new VideoResultsFragment());

        VPAdapter vpAdapter = new VPAdapter(ResultsActivity.this);
        binding.viewPager2.setAdapter(vpAdapter);

        fragmentTitleList.add(getString(R.string.fragment_title_all));
        fragmentTitleList.add(getString(R.string.fragment_title_images));
        fragmentTitleList.add(getString(R.string.fragment_title_videos));

        new TabLayoutMediator(binding.tabLayout, binding.viewPager2, (tab, position) -> {
            tab.setText(fragmentTitleList.get(position));
        }).attach();
    }

    private class VPAdapter extends FragmentStateAdapter {
        public VPAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getItemCount() {
            return fragmentList.size();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}