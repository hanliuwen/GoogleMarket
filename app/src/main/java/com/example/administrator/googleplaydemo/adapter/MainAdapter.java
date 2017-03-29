package com.example.administrator.googleplaydemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.administrator.googleplaydemo.factory.FragmentFactory;


/**
 * Created by Leon on 2017/3/26.
 */

public class MainAdapter extends FragmentPagerAdapter {

    private static final String TAG = "MainAdapter";

    private String[] dataList;

    public MainAdapter(String[] dataList, FragmentManager fm) {
        super(fm);
        this.dataList = dataList;
    }

    /**
     * 返回对应位置的Fragment
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "getItem: " + position);
//        return FragmentFactory.getFragment(position);
        return FragmentFactory.getInstance().getFragment(position);
    }

    @Override
    public int getCount() {
        return dataList.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return dataList[position];
    }
}
