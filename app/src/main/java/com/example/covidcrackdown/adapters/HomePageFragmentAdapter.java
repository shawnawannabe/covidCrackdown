package com.example.covidcrackdown.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.covidcrackdown.fragments.ThingsToDoFragment;
import com.example.covidcrackdown.fragments.ThingsToKnowFragment;

public class HomePageFragmentAdapter extends FragmentStateAdapter {
    public HomePageFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new ThingsToDoFragment();
        }
        return new ThingsToKnowFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
