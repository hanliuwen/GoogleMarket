package com.example.administrator.googleplaydemo.factory;


import android.support.v4.app.Fragment;

import com.example.administrator.googleplaydemo.ui.fragment.AppFragment;
import com.example.administrator.googleplaydemo.ui.fragment.CategoryFragment;
import com.example.administrator.googleplaydemo.ui.fragment.GameFragment;
import com.example.administrator.googleplaydemo.ui.fragment.HomeFragment;
import com.example.administrator.googleplaydemo.ui.fragment.HotFragment;
import com.example.administrator.googleplaydemo.ui.fragment.RecommendFragment;
import com.example.administrator.googleplaydemo.ui.fragment.SubjectFragment;


/**
 * Created by Administrator on 2017/3/26.
 */

public class FragmentFactory {

    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_APP = 1;
    private static final int FRAGMENT_GAME = 2;
    private static final int FRAGMENT_SUBJECT = 3;
    private static final int FRAGMENT_RECOMMEND = 4;
    private static final int FRAGMENT_CATEGORY = 5;
    private static final int FRAGMENT_HOT = 6;

    //单例模式，一个app中只存在一个FragmentFactory实例
    private static FragmentFactory sFragmentFactory;

    public static FragmentFactory getInstance() {
        if (sFragmentFactory == null) {
            //只需要在sFragmentFactory为空时候才加锁创建就可以
            synchronized (FragmentFactory.class) {
                if (sFragmentFactory == null) {
                    sFragmentFactory = new FragmentFactory();
                }
            }
        }

        return sFragmentFactory;
    }

    /**
     * 根据不同的位置生产出不同的Fragment
     * @param position fragment位置
     */
    public Fragment getFragment(int position) {
        switch (position) {
            case FRAGMENT_HOME:
                return new HomeFragment();
            case FRAGMENT_APP:
                return new AppFragment();
            case FRAGMENT_GAME:
                return new GameFragment();
            case FRAGMENT_SUBJECT:
                return new SubjectFragment();
            case FRAGMENT_RECOMMEND:
                return new RecommendFragment();
            case FRAGMENT_CATEGORY:
                return new CategoryFragment();
            case FRAGMENT_HOT:
                return new HotFragment();
        }

        return null;
    }
}
