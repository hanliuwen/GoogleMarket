package com.example.administrator.googleplaydemo.adapter;

import android.content.Context;

import com.example.administrator.googleplaydemo.bean.SubjectItemBean;
import com.example.administrator.googleplaydemo.widget.SubjectListItemView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29.
 */

public class SubjectAdapter extends BaseLoadMoreListAdapter<SubjectItemBean> {

    public SubjectAdapter(Context context, List<SubjectItemBean> dataList) {
        super(context, dataList);
    }

    /**
     * 创建一个normal item的viewHolder
     * @return
     */
    @Override
    protected ViewHolder onCreteNormalViewHolder() {
        return new ViewHolder(new SubjectListItemView(getContext()));
    }

    @Override
    protected void onBindNormalViewHolder(ViewHolder viewHolder,int position) {
        ((SubjectListItemView)viewHolder.mView).bindView(getDataList().get(position));
    }
}
