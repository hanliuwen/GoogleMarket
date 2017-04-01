package com.example.administrator.googleplaydemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.Button;

import com.example.administrator.googleplaydemo.R;
import com.example.administrator.googleplaydemo.bean.AppDetailBean;
import com.example.administrator.googleplaydemo.bean.DownloadInfo;
import com.example.administrator.googleplaydemo.manager.DownloadManager;

/**
 * Created by Administrator on 2017/3/31.
 */

public class DownloadButton extends Button {

    private ColorDrawable mColorDrawable;
    private Paint mPaint;

    private int mProgress;
    private int mMax;

    public DownloadButton(Context context) {
        this(context, null);
    }

    public DownloadButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mColorDrawable = new ColorDrawable(Color.BLUE);
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画一个矩形表示进度
        // canvas.drawRect(0, 0, 200, getHeight(), mPaint);
        float percent = mProgress * 1.0f / mMax;
        float right = getWidth() * percent;
        mColorDrawable.setBounds(0, 0, (int) right, getHeight());
        mColorDrawable.draw(canvas);
        super.onDraw(canvas);
    }

    /**
     * 设置进度
     */
    public void setProgress(int progress) {
        //计算进度的百分比
        mProgress = progress;
        int percent = (int) (mProgress * 1.0f / mMax * 100);
        String percentString = String.format(getResources().getString(R.string.download_progress), percent);
        setText(percentString);
        invalidate();
    }

    @Override
    public int getHorizontalFadingEdgeLength() {
        return super.getHorizontalFadingEdgeLength();
    }

    public void setMax(int max) {
        mMax = max;
    }

    /**
     * 同步DownloadButton下载状态
     * @param appDetailBean
     */
    public void syncState(AppDetailBean appDetailBean) {
        //初始化DownloadInfo下载信息
        DownloadInfo downloadInfo = DownloadManager.getInstance().initDownloadInfo(getContext(),
                appDetailBean.getPackageName(),
                appDetailBean.getSize(),
                appDetailBean.getDownloadUrl());
        //根据初始化的结果，刷新界面状态
        updateStatus(downloadInfo);
    }

    private void updateStatus(DownloadInfo downloadInfo) {
        switch (downloadInfo.getStatus()) {

            case DownloadManager.STATE_INSTALLED:
                setText(getResources().getString(R.string.open));
                break;

            case DownloadManager.STATE_DOWNLOADED:
                setText(getResources().getString(R.string.install));//如果下载完成,则显示安装
                break;

            case DownloadManager.STATE_UN_DOWNLOAD:
                setText(getResources().getString(R.string.download));
                break;
        }

    }
}
