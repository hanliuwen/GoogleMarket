package com.example.administrator.googleplaydemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;


/**
 * Created by Administrator on 2017/3/28.
 */

public abstract class BaseListAdapter<T> extends BaseAdapter {

    private Context mContext;
    private List<T> mDataList;

    public BaseListAdapter(Context context, List<T> dataList) {
        mContext = context;
        mDataList = dataList;
    }

    @Override
    public int getCount() {
        if (mDataList == null) {
            return 0;
        }
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            //基类里面不知道具体list item的视图长相,需要子类去实现,子类实现一个viewhoder,holder item视图
            viewHolder = onCreateViewHolder(position);
            //子类实现了viewholder,创建一个item view,并且hold住
            convertView = viewHolder.mView;

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //绑定一下hold住的view
//        viewHolder.mView.setText();
        //让子类来实现view的绑定,根据位置position,拿到对应位置的数据,来绑定对应位置item的视图
        onBindViewHolder(viewHolder, position);
        return convertView;
    }

    /**
     * 绑定对应位置的item view
     */
    abstract void onBindViewHolder(ViewHolder viewHolder, int position);

    /**
     * 创建一个viewholder holder 对应位置item view
     */
    abstract ViewHolder onCreateViewHolder(int position);

    public class ViewHolder {
        View mView;

        public ViewHolder (View root) {
            mView = root;
        }
    }

    protected List<T> getDataList() {
        return mDataList;
    }

    protected Context getContext() {
        return mContext;
    }
}
