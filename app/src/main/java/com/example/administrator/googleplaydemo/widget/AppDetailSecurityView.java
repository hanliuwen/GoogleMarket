package com.example.administrator.googleplaydemo.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.administrator.googleplaydemo.R;
import com.example.administrator.googleplaydemo.app.Constant;
import com.example.administrator.googleplaydemo.bean.AppDetailBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/30.
 */

public class AppDetailSecurityView extends RelativeLayout {

    @BindView(R.id.tag_container)
    LinearLayout mTagContainer;
    @BindView(R.id.security_arrow)
    ImageView mSecurityArrow;
    @BindView(R.id.security_info_container)
    LinearLayout mSecurityInfoContainer;

    private boolean isOpen = false;//展开的状态标记

    public AppDetailSecurityView(Context context) {
        this(context, null);
    }

    public AppDetailSecurityView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_app_detail_security, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(AppDetailBean appDetailBean) {
        for (int i = 0; i < appDetailBean.getSafe().size(); i++) {
            //获取数组里面元素
            AppDetailBean.SafeBean safeBean = appDetailBean.getSafe().get(i);
            //添加标签
            ImageView imageView = new ImageView(getContext());
            //加载图片
            String url = Constant.URL_IMAGE + safeBean.getSafeUrl();
            Glide.with(getContext()).load(url).override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).into(imageView);
            //将标签添加tag 容器里面
            mTagContainer.addView(imageView);

            //添加一行描述
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            //创建一个网络图片checkbox
            ImageView checBox = new ImageView(getContext());
            String checkBoxUrl = Constant.URL_IMAGE + safeBean.getSafeDesUrl();
            Glide.with(getContext()).load(checkBoxUrl).override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).into(checBox);
            //将checkbox网络图片加入到一行
            linearLayout.addView(checBox);
            //添加一个描述
            TextView textView = new TextView(getContext());
            textView.setText(safeBean.getSafeDes());

            //如果safeDesColor值不等于0表示是有问题，要变化文本颜色 提示用户
            if (safeBean.getSafeDesColor() != 0) {
                textView.setTextColor(getResources().getColor(R.color.app_detail_safe_warning));
            } else {
                textView.setTextColor(getResources().getColor(R.color.app_detail_safe_normal));
            }

            //将描述加入一行
            linearLayout.addView(textView);
            //将一行描述加入容器
            mSecurityInfoContainer.addView(linearLayout);
        }
    }


    @OnClick(R.id.security_arrow)
    public void onViewClicked() {
        toogle();//展开或者收缩安全描述
    }

    private void toogle() {

        if (isOpen) {
            //关闭

        } else {
            //展开
            //高度从0到原始高度（展开后的高度）
            //测量描述容器展开后的高度
            // 指定期望是不限制大小 MeasureSpec.UNSPECIFIED;
            // MeasureSpec.makeMeasureSpec(0, 0); = 0
            mSecurityInfoContainer.measure(0,0);
            //获取展开后的高度
            int measuredHeight = mSecurityInfoContainer.getMeasuredHeight();
            //产生动画需要数据序列（0， measureHeight）
            ValueAnimator valueAnimator = ValueAnimator.ofInt(0, measuredHeight);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int value = (int) animation.getAnimatedValue();
                }
            });
            //启动animator
            valueAnimator.start();
        }

    }
}
