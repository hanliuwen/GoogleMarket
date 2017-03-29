package com.example.administrator.googleplaydemo.ui.fragment;


import android.view.View;

import com.example.administrator.googleplaydemo.app.Constant;
import com.example.administrator.googleplaydemo.bean.HomeBean;
import com.example.administrator.googleplaydemo.network.MyRetrofit;
import com.leon.loopviewpagerlib.FunBanner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/3/26.
 */
public class HomeFragment extends BaseAppListFragment {

    private List<String> mPicture;

    @Override
    protected View onCreateHeaderView() {
        return new FunBanner.Builder(getContext())
                .setHeightWidthRatio(0.377f)
                .setEnableAutoLoop(true)
                .setImageUrlHost(Constant.URL_IMAGE)
                .setImageUrls(mPicture)
                .build();
    }

    @Override
    protected void startLoadData() {
        Call<HomeBean> homeBeanCall = MyRetrofit.getInstance().getApi().listHome(0);
        homeBeanCall.enqueue(new Callback<HomeBean>() {
            @Override
            public void onResponse(Call<HomeBean> call, Response<HomeBean> response) {
                getDataList().addAll(response.body().getList());
                //保存轮播图数据
                mPicture = response.body().getPicture();
                OnDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<HomeBean> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onStartLoadMore() {
        Call<HomeBean> homeBeanCall = MyRetrofit.getInstance().getApi().listHome(getDataList().size());
        homeBeanCall.enqueue(new Callback<HomeBean>() {
            @Override
            public void onResponse(Call<HomeBean> call, Response<HomeBean> response) {
                getDataList().addAll(response.body().getList());
                getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<HomeBean> call, Throwable t) {

            }
        });
    }
}
