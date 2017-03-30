package com.example.administrator.googleplaydemo.ui.activity;

import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.administrator.googleplaydemo.R;

/**
 * Created by Administrator on 2017/3/30.
 */
public class AppDetailActivity extends BaseActivity{

    private Toolbar mToolBar;

    @Override
    public int getlayoutResId() {
        return R.layout.activity_app_detail;
    }

    @Override
    protected void init() {
        super.init();
        String extra = getIntent().getStringExtra("package_name");
        Toast.makeText(this, extra, Toast.LENGTH_SHORT).show();
        initToolbar();
        setStatusBarColor();
    }

    private void initToolbar() {
        //用ToolBar代替actionbar
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.app_detail));
        //设置返回按钮
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    private void setStatusBarColor() {
        //只要api level >21 才能写代码配置状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //获取activity对应phone window
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
    }
}
