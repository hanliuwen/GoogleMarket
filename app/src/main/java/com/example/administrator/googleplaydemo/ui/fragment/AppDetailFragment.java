package com.example.administrator.googleplaydemo.ui.fragment;

import android.view.View;

import com.example.administrator.googleplaydemo.R;
import com.example.administrator.googleplaydemo.bean.AppDetailBean;
import com.example.administrator.googleplaydemo.network.MyRetrofit;
import com.example.administrator.googleplaydemo.widget.AppDetailBottomBar;
import com.example.administrator.googleplaydemo.widget.AppDetailDesView;
import com.example.administrator.googleplaydemo.widget.AppDetailGalleryView;
import com.example.administrator.googleplaydemo.widget.AppDetailInfoView;
import com.example.administrator.googleplaydemo.widget.AppDetailSecurityView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/3/30.
 */

public class AppDetailFragment extends BaseFragment {

    private AppDetailBean mAppDetailBean;

    @Override
    protected void startLoadData() {
        //获取包名
        String packageName = getActivity().getIntent().getStringExtra("package_name");
        Call<AppDetailBean> appDetail = MyRetrofit.getInstance().getApi().getAppDetail(packageName);
        appDetail.enqueue(new Callback<AppDetailBean>() {
            @Override
            public void onResponse(Call<AppDetailBean> call, Response<AppDetailBean> response) {
                mAppDetailBean = response.body();
                OnDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<AppDetailBean> call, Throwable t) {

            }
        });
    }

    @Override
    protected View onCreateContentView() {
        View view = View.inflate(getContext(), R.layout.app_detail_content, null);

        //应用信息
        AppDetailInfoView appDetailInfoView = (AppDetailInfoView) view.findViewById(R.id.app_detail_info);
        appDetailInfoView.bindView(mAppDetailBean);

        //应用安全
        AppDetailSecurityView appDetailSecurityView = (AppDetailSecurityView) view.findViewById(R.id.app_detail_security);
        appDetailSecurityView.bindView(mAppDetailBean);

        //应用截图
        AppDetailGalleryView appDetailGalleryView = (AppDetailGalleryView) view.findViewById(R.id.app_detail_gallery);
        appDetailGalleryView.bindView(mAppDetailBean);

        //应用描述
        AppDetailDesView appDetailDesView = (AppDetailDesView) view.findViewById(R.id.app_detail_des);
        appDetailDesView.bindView(mAppDetailBean);

        //底部条
        AppDetailBottomBar appDetailBottomBar = (AppDetailBottomBar) view.findViewById(R.id.app_detail_bottom_bar);
        appDetailBottomBar.bindView(mAppDetailBean);

        return view;
    }
}
