package com.example.administrator.googleplaydemo.adapter;

import android.content.Context;

import com.example.administrator.googleplaydemo.bean.AppListItemBean;
import com.example.administrator.googleplaydemo.widget.AppListItemView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29.
 */
//由于首页，游戏，应用3个页面的item长得一样，说明adpater一样，可以抽取一个公共的adapter
public class AppListAdapter extends BaseLoadMoreListAdapter<AppListItemBean> {

    public AppListAdapter(Context context, List<AppListItemBean> dataList) {
        super(context, dataList);
    }

    @Override
    protected ViewHolder onCreteNormalViewHolder() {
        return new ViewHolder(new AppListItemView(getContext()));
    }

    @Override
    protected void onBindNormalViewHolder(ViewHolder viewHolder, int position) {
        ((AppListItemView)viewHolder.mView).bindView(getDataList().get(position));
    }

}
