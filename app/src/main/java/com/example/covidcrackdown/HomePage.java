package com.example.covidcrackdown;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePage extends Fragment {

    //tab variable
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    HomePageFragmentAdapter adapter;
    //icon variable
    LinearLayout hotSpotLinearLayout;
    LinearLayout faqsLinearLayout;
    LinearLayout statsLinearLayout;

    public HomePage() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomePage.
     */
    public static HomePage newInstance(String param1, String param2) {
        HomePage fragment = new HomePage();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getParentFragmentManager();
        adapter = new HomePageFragmentAdapter(fm, getLifecycle());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        //tab
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager2 = view.findViewById(R.id.viewpager2);
        viewPager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Things to know"));
        tabLayout.addTab(tabLayout.newTab().setText("Things to do"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        //icon click event
        hotSpotLinearLayout = view.findViewById(R.id.hot_spot);
        faqsLinearLayout = view.findViewById(R.id.faqs);
        statsLinearLayout = view.findViewById(R.id.stats);

        hotSpotLinearLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openHotSpotActivity();
            }
        });

        faqsLinearLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openFaqsActivity();
            }
        });

        statsLinearLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openStatsActivity();
            }
        });
        return view;
    }

    private void openFaqsActivity() {
        Intent intent = new Intent(getActivity(), Faqs.class);
        startActivity(intent);
    }

    public void openHotSpotActivity(){
        Intent intent = new Intent(getActivity(), HotSpot.class);
        startActivity(intent);
    }

    public void openStatsActivity(){
        Intent intent = new Intent(getActivity(), Statistic.class);
        startActivity(intent);
    }

}