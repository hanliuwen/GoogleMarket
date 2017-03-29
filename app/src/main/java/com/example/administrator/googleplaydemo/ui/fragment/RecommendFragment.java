package com.example.administrator.googleplaydemo.ui.fragment;

import android.content.Context;
import android.view.View;

import com.example.administrator.googleplaydemo.R;
import com.example.administrator.googleplaydemo.adapter.RecommendAdapter;
import com.example.administrator.googleplaydemo.network.MyRetrofit;
import com.example.administrator.googleplaydemo.widget.StellarMap;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/3/26.
 */
public class RecommendFragment extends BaseFragment {

    private Context mContext;
    private List<String> mDataList;

    private static final int PAGE_SIZE = 15;

    @Override
    protected void startLoadData() {
        Call<List<String>> listCall = MyRetrofit.getInstance().getApi().listRecommend();
        listCall.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                mDataList = response.body();
                OnDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                onDataLoadedFailed();
            }
        });
    }

    @Override
    protected View onCreateContentView() {
        //创建星状图
        StellarMap stellarMap = new StellarMap(getContext());
        int padding = getResources().getDimensionPixelOffset(R.dimen.padding);
        stellarMap.setInnerPadding(padding,padding,padding,padding);
        stellarMap.setAdapter(new RecommendAdapter(getContext(),mDataList));

        //设置分布网络
        stellarMap.setRegularity(15,20);
        //设置初始化页面
        stellarMap.setGroup(0,false);
        return stellarMap;
    }

}
