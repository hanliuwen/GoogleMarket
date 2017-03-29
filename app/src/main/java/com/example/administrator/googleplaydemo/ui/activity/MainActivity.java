package com.example.administrator.googleplaydemo.ui.activity;


import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.googleplaydemo.R;
import com.example.administrator.googleplaydemo.adapter.MainAdapter;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;

    private String[] mTitles;
    private ActionBar supportActionBar;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    public int getlayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();
        mNavigationView.setNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mTitles = getResources().getStringArray(R.array.main_titles);
        initActionBar();
        initViewPager();
    }

    private void initViewPager() {
        mViewPager.setAdapter(new MainAdapter(mTitles,getSupportFragmentManager()));
        //关联TabLayout和ViewPager
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView textView = new TextView(MainActivity.this);
            textView.setText(mTitles[position]);

            container.addView(textView);
            return textView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    };

    private void initActionBar() {
        //将Toolbar替换成ActionBar，原来ActionBar的操作全部换成操作Toolbar
        setSupportActionBar(mToolBar);
        supportActionBar = getSupportActionBar();

        //设置返回按钮
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        //DrawerLayout和ActionBar的联动
        // ActionBarDrawerToggle能够拿到Activity，所以能够拿到ActionBar去设置图片
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        //同步DrawerLayout状态，来显示成不同图片
        mActionBarDrawerToggle.syncState();

        //注册DrawerLayout监听器，监听DrawerLayout动作. mActionBarDrawerToggle要监听Drawerlayout
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
    }

    /**
     * 创建菜单选项
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu,menu);
        return true;
    }

    /**
     * ActionBar菜单按钮点击事件回调
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //打开或者关闭侧滑菜单, 内部封装了mDrawerLayout.openDrawer(), mDrawerLayout.closeDrawer();
                mActionBarDrawerToggle.onOptionsItemSelected(item);
                break;

            case R.id.menu_test:
                Toast.makeText(this, "Menu Test Click", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    private NavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {

        /**
         *
         * @param item 点击的菜单选项
         * @return true将点击选项显示成选中状态
         */
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return true;
        }
    };

}
