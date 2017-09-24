package com.fladimir.worktime.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LanFl on 2017/9/24.
 */

public class BaseFragmentAdapter extends FragmentPagerAdapter {
    List<String> titles = new ArrayList<>();
    List<Fragment> fragments = new ArrayList<>();

    public BaseFragmentAdapter(FragmentManager fm, List<String> titles, List<Fragment> fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    public BaseFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        //this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    /*@Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }*/
}
