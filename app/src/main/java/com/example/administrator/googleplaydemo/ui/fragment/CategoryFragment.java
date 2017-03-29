package com.example.administrator.googleplaydemo.ui.fragment;


import android.widget.BaseAdapter;

import com.example.administrator.googleplaydemo.adapter.CategoryAdapter;
import com.example.administrator.googleplaydemo.bean.CategoryItemBean;
import com.example.administrator.googleplaydemo.network.MyRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/3/26.
 */
public class CategoryFragment extends BaseListFragment {

    private List<CategoryItemBean> mDataList;

    @Override
    protected void startLoadData() {
        Call<List<CategoryItemBean>> listCall = MyRetrofit.getInstance().getApi().listCategory();
        listCall.enqueue(new Callback<List<CategoryItemBean>>() {
            @Override
            public void onResponse(Call<List<CategoryItemBean>> call, Response<List<CategoryItemBean>> response) {
                mDataList = response.body();
                OnDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<List<CategoryItemBean>> call, Throwable t) {
                onDataLoadedFailed();
            }
        });
    }

    /**
     * 创建分类界面adpater
     */
    @Override
    public BaseAdapter onCreateAdapter() {
        return new CategoryAdapter(getContext(),mDataList);
    }
}
