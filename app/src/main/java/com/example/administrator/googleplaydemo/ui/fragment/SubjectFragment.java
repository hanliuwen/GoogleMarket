package com.example.administrator.googleplaydemo.ui.fragment;

import android.widget.BaseAdapter;

import com.example.administrator.googleplaydemo.adapter.SubjectAdapter;
import com.example.administrator.googleplaydemo.bean.SubjectItemBean;
import com.example.administrator.googleplaydemo.network.MyRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/3/26.
 */
public class SubjectFragment extends BaseLoadMoreListFragment {

    private static final String TAG = "SubjectFragment";

    private List<SubjectItemBean> mDataList;

    @Override
    protected void startLoadData() {
        Call<List<SubjectItemBean>> listCall = MyRetrofit.getInstance().getApi().listSubject(0);
        listCall.enqueue(new Callback<List<SubjectItemBean>>() {
            @Override
            public void onResponse(Call<List<SubjectItemBean>> call, Response<List<SubjectItemBean>> response) {
                mDataList = response.body();
                OnDataLoadedSuccess();

            }

            @Override
            public void onFailure(Call<List<SubjectItemBean>> call, Throwable t) {
                onDataLoadedFailed();
            }
        });
    }

    @Override
    protected void onStartLoadMore() {

        Call<List<SubjectItemBean>> listCall = MyRetrofit.getInstance().getApi().listSubject(mDataList.size());
        listCall.enqueue(new Callback<List<SubjectItemBean>>() {
            @Override
            public void onResponse(Call<List<SubjectItemBean>> call, Response<List<SubjectItemBean>> response) {
                mDataList.addAll(response.body());
                getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<SubjectItemBean>> call, Throwable t) {

            }
        });
    }

    @Override
    public BaseAdapter onCreateAdapter() {
        return new SubjectAdapter(getContext(),mDataList);
    }

}
