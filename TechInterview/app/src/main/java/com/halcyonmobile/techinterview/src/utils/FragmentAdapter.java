package com.halcyonmobile.techinterview.src.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by zmate on 12/12/2016.
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super( fm );
        this.fragmentList = fragmentList;
    }

    public float getPageWidth(int position) {
        return 0.92f;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragmentList.get( position );
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
