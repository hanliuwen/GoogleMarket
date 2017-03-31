package com.example.administrator.googleplaydemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.googleplaydemo.R;
import com.example.administrator.googleplaydemo.bean.AppDetailBean;
import com.example.administrator.googleplaydemo.utils.AnimationUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/31.
 */

public class AppDetailDesView extends RelativeLayout {

    @BindView(R.id.app_detail_des_text)
    TextView mAppDetailDesText;
    @BindView(R.id.app_name)
    TextView mAppName;
    @BindView(R.id.des_arrow)
    ImageView mDesArrow;

    private static final int MAX_LINES = 7;
    private boolean isOpen = false;
    private int mOriginalHeight = 0;// 保存初始化高度

    public AppDetailDesView(Context context) {
        this(context, null);
    }

    public AppDetailDesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_app_detail_des, this);
        ButterKnife.bind(this, this);
        //监听布局完成，设置初始化行高
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //只监听一次
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
                //布局完成之后，在设置行高之前，我保存下初始高度
                mOriginalHeight = mAppDetailDesText.getMeasuredHeight();
                //如果描述的行数大于7行，初始化时就设置成7行
                int lineCount = mAppDetailDesText.getLineCount();
                if (lineCount > MAX_LINES) {
                    mAppDetailDesText.setLines(MAX_LINES);
                }
            }
        });
    }

    public void bindView(AppDetailBean appDetailBean) {
        mAppDetailDesText.setText(appDetailBean.getDes());
        mAppName.setText(appDetailBean.getName());

    }

    @OnClick(R.id.des_arrow)
    public void onViewClicked() {
        toggle();
    }

    private void toggle() {
        if (isOpen) {
            int start = mOriginalHeight;
            if (mAppDetailDesText.getLineCount() > MAX_LINES) {
                mAppDetailDesText.setLines(7);
                mAppDetailDesText.measure(0, 0);
            }
            int end = mAppDetailDesText.getMeasuredHeight();
            AnimationUtil.animationViewHeight(mAppDetailDesText, start, end);
        }else {
            int measuredHeight = mAppDetailDesText.getMeasuredHeight();
            AnimationUtil.animationViewHeight(mAppDetailDesText, measuredHeight, mOriginalHeight);
        }

        isOpen = !isOpen;
    }
}
