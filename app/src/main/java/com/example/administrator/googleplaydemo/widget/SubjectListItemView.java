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
import com.example.administrator.googleplaydemo.bean.SubjectItemBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/29.
 */

public class SubjectListItemView extends RelativeLayout {

    @BindView(R.id.icon)
    ImageView mIcon;
    @BindView(R.id.title)
    TextView mTitle;

    public SubjectListItemView(Context context) {
        this(context, null);
    }

    public SubjectListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(),R.layout.list_subject_item, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(SubjectItemBean bean) {
        mTitle.setText(bean.getDes());
        Glide.with(getContext()).load(Constant.URL_IMAGE + bean.getUrl()).into(mIcon);
    }
}
