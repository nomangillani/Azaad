package com.example.azaad;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class ChatFragment extends Fragment {
    View view;
    ViewPager simpleViewPager;
    TabLayout tabLayout;
    FragmentPagerAdapter adapterViewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chatfragment, container, false);
        simpleViewPager = view.findViewById(R.id.simpleViewPager);
        tabLayout = view.findViewById(R.id.tabs);
        /*tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#000000"));

        tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#000000"));*/
        tabLayout.setupWithViewPager(simpleViewPager);
        PagerAdapter adapter = new PagerAdapter
                (getFragmentManager(), tabLayout.getTabCount());
        simpleViewPager.setAdapter(adapter);
// addOnPageChangeListener event change the tab on slide
        simpleViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



        return view;
    }
}