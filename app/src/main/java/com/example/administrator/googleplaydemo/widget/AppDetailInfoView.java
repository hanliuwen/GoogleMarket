package com.example.administrator.googleplaydemo.widget;

import android.content.Context;
import android.text.format.Formatter;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.googleplaydemo.R;
import com.example.administrator.googleplaydemo.app.Constant;
import com.example.administrator.googleplaydemo.bean.AppDetailBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/30.
 */

public class AppDetailInfoView extends RelativeLayout {


    @BindView(R.id.app_icon)
    ImageView mAppIcon;
    @BindView(R.id.app_name)
    TextView mAppName;
    @BindView(R.id.app_rating)
    RatingBar mAppRating;
    @BindView(R.id.app_download_count)
    TextView mAppDownloadCount;
    @BindView(R.id.app_version)
    TextView mAppVersion;
    @BindView(R.id.timestamp)
    TextView mTimestamp;
    @BindView(R.id.app_size)
    TextView mAppSize;

    public AppDetailInfoView(Context context) {
        this(context, null);
    }

    public AppDetailInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_app_detail_info, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(AppDetailBean appDetailBean) {
        String url = Constant.URL_IMAGE + appDetailBean.getIconUrl();
        Glide.with(getContext()).load(url).into(mAppIcon);

        mAppName.setText(appDetailBean.getName());
        mAppRating.setRating(appDetailBean.getStars());

        //刷新下载量
        //格式化下载量的文本
        String formatString = getResources().getString(R.string.download_count);
        String formatResult = String.format(formatString, appDetailBean.getDownloadNum());
        mAppDownloadCount.setText(formatResult);

        //版本号
        String version = String.format(getResources().getString(R.string.version_code), appDetailBean.getVersion());
        mAppVersion.setText(version);

        //时间
        String date = String.format(getResources().getString(R.string.time), appDetailBean.getDate());
        mTimestamp.setText(date);

        //大小
        String size = String.format(getResources().getString(R.string.app_size), Formatter.formatFileSize(getContext(), appDetailBean.getSize()));
        mAppSize.setText(size);
    }
}
