package com.example.administrator.googleplaydemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.googleplaydemo.widget.StellarMap;

import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/3/27.
 */

public class RecommendAdapter implements StellarMap.Adapter {

    private Context mContext;
    private List<String> mDataList;
    private static final int PAGE_SIZE = 15;

    public RecommendAdapter(Context context, List<String> dataList) {
        mContext = context;
        mDataList = dataList;

    }

    /**
     *  返回组（页面的）个数
     */
    @Override
    public int getGroupCount() {
        int count = mDataList.size() / PAGE_SIZE;
        //如果模上数据集合大小有余数，则加一个页面
        if (mDataList.size() % PAGE_SIZE != 0) {
            count++;
        }
        return count;
    }

    /**
     * @param group 页面的下标
     * @return 对应页面（组）的元素的个数
     */
    @Override
    public int getCount(int group) {
        //当有余数且等于最后一组，返回取模的结果
        if (mDataList.size() % PAGE_SIZE != 0) {
            if (group == getGroupCount() - 1) {
                return mDataList.size() % PAGE_SIZE;
            }
        }
        //默认返回页面默认元素个数
        return PAGE_SIZE;
    }

    @Override
    public View getView(int group, int position, View convertView) {
        if (convertView == null) {
            convertView = new TextView(mContext);
        }
        //设置textview数据
        TextView textView = (TextView) convertView;
        //计算元素在数据集合中对应数据下标
        int pos = group * PAGE_SIZE + position;
        textView.setText(mDataList.get(pos));

        //给TextView设置随机的颜色
        textView.setTextColor(getArgb());
        //给TextView设置随机的大小 14-18
        int size = 14 + new Random().nextInt(4);
        textView.setTextSize(size);
        return textView;
    }

    private int getArgb() {
        int alpha = 255;
        int red = 30 + new Random().nextInt(200);//30到230
        int green = 30 + new Random().nextInt(200);
        int blue = 30 + new Random().nextInt(200);
        return Color.argb(alpha, red, green, blue);
    }

    @Override
    public int getNextGroupOnPan(int group, float degree) {
        return 0;
    }

    /**
     *
     * @param group 当前页面的下标
     * @param isZoomIn 是不是放大
     * @return 根据动画的类型返回下一个页面的下标
     */
    @Override
    public int getNextGroupOnZoom(int group, boolean isZoomIn) {
        if (isZoomIn) {
            return (group + 1) % getGroupCount();
        }else {
            //缩小
            // 0  -- > 2    (-1 + 3) % 3 = 2
            // 2  -- > 1 (1 + 3) % 3 = 1
            // 1 -- > 0  3 % 3 = 0
            //0 -->２
            return (group - 1 + getGroupCount()) % getGroupCount();
        }
    }
}
