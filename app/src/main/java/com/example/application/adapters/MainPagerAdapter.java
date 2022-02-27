package com.example.application.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.application.R;
import com.example.application.fragments.MoviesFragment;
import com.example.application.fragments.PeopleFragment;
import com.example.application.fragments.TVSeriesFragment;

import java.util.ArrayList;

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();

    public MainPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        fragments.add(new MoviesFragment());
        titles.add(context.getString(R.string.movies));
        fragments.add(new TVSeriesFragment());
        titles.add(context.getString(R.string.tv_series));
        fragments.add(new PeopleFragment());
        titles.add(context.getString(R.string.people));
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
