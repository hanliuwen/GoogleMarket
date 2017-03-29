package com.example.administrator.googleplaydemo.ui.fragment;

import com.example.administrator.googleplaydemo.bean.AppListItemBean;
import com.example.administrator.googleplaydemo.network.MyRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/3/26.
 */
public class GameFragment extends BaseAppListFragment {

    @Override
    protected void startLoadData() {
        Call<List<AppListItemBean>> listCall = MyRetrofit.getInstance().getApi().listGame(0);
        listCall.enqueue(new Callback<List<AppListItemBean>>() {
            @Override
            public void onResponse(Call<List<AppListItemBean>> call, Response<List<AppListItemBean>> response) {
                getDataList().addAll(response.body());
                OnDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<List<AppListItemBean>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onStartLoadMore() {
        Call<List<AppListItemBean>> listCall = MyRetrofit.getInstance().getApi().listGame(getDataList().size());
        listCall.enqueue(new Callback<List<AppListItemBean>>() {
            @Override
            public void onResponse(Call<List<AppListItemBean>> call, Response<List<AppListItemBean>> response) {
                getDataList().addAll(response.body());
                //刷新加载更多数据的列表
                getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<AppListItemBean>> call, Throwable t) {

            }
        });
    }
}
