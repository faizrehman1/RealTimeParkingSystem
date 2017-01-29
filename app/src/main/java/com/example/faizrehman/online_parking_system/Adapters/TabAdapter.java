package com.example.faizrehman.online_parking_system.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by faizrehman on 1/25/17.
 */

public class TabAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> arrayList;

    public TabAdapter(FragmentManager fm, ArrayList<Fragment> fragmentArrayListl) {
        super(fm);
        this.arrayList = fragmentArrayListl;
    }

    @Override
    public Fragment getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }
}
