package com.example.administrator.googleplaydemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.googleplaydemo.R;
import com.example.administrator.googleplaydemo.app.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/28.
 */

public class CategoryItemInfoView extends RelativeLayout {

    @BindView(R.id.icon)
    ImageView mIcon;
    @BindView(R.id.title)
    TextView mTitle;

    public CategoryItemInfoView(Context context) {
        this(context, null);
    }

    public CategoryItemInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_category_info_item, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(String name, String url) {
        //刷新标题
        mTitle.setText(name);
        //绑定图片
        String imageUrl = Constant.URL_IMAGE + url;
        Glide.with(getContext()).load(imageUrl).placeholder(R.drawable.ic_default).centerCrop().into(mIcon);
    }
}
