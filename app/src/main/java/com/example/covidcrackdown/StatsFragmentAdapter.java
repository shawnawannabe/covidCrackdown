package com.example.covidcrackdown;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class StatsFragmentAdapter extends FragmentStateAdapter {
    public StatsFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new GlobalStats();
        }
        return new LocalStats();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
