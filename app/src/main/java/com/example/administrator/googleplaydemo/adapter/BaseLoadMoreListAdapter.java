package com.example.administrator.googleplaydemo.adapter;

import android.content.Context;

import com.example.administrator.googleplaydemo.widget.LoadingMoreProgressView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29.
 */

//由于列表的item的视图都是由Adapter来决定的，且首页，应用，游戏，专题都有一个加载进度条，
// 所以可以抽取一个Adapter来封装加载进度条的创建
//ListView的多条目的实现
public abstract class BaseLoadMoreListAdapter<T> extends BaseListAdapter<T> {

    private static final int ITEM_TYPE_NORMAL = 0;
    private static final int ITEM_TYPE_PROGRESS = 1;

    public BaseLoadMoreListAdapter(Context context, List<T> dataList) {
        super(context, dataList);
    }

    @Override
    void onBindViewHolder(ViewHolder viewHolder, int position) {
        //一般情况下就不绑定进度条,只绑定normal item
        if (getItemViewType(position) == ITEM_TYPE_NORMAL) {
            //子类来实现普通类型的item的绑定
            onBindNormalViewHolder(viewHolder,position);
        }
    }

    @Override
    ViewHolder onCreateViewHolder(int position) {
        if (getItemViewType(position) == ITEM_TYPE_NORMAL) {
            //创建正常item的ViewHolder
           return onCreteNormalViewHolder();
        }else {
            //由于页面的进度条一样.可以在这里实现一个ViewHolder
            return new ViewHolder(new LoadingMoreProgressView(getContext()));
        }
    }

    /**
     *  创建普通的item的ViewHolder
     */
    protected abstract ViewHolder onCreteNormalViewHolder();

    /**
     * 绑定普通的ViewHolder
     */
    protected abstract void onBindNormalViewHolder(ViewHolder viewHolder,int position);

    /**
     * 由于多了一个进度条,所以个数要加1
     * @return
     */
    @Override
    public int getCount() {
        if (getDataList() == null) {
            return 0;
        }
        return getDataList().size() + 1;
    }

    /**
     *  返回条目的类型个数，这里有两种类型的条目，一种是正常的item, 一种是进度条条目
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    /**
     * 复写方法返回对应位置item的类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == getCount() - 1) {
            return ITEM_TYPE_PROGRESS;
        }else {
            return ITEM_TYPE_NORMAL;
        }
    }


}
