package com.example.administrator.googleplaydemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.googleplaydemo.R;
import com.example.administrator.googleplaydemo.bean.AppListItemBean;
import com.example.administrator.googleplaydemo.bean.DownloadInfo;
import com.example.administrator.googleplaydemo.manager.DownloadManager;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/29.
 */

public class CircleDownloadView extends RelativeLayout implements Observer {

    @BindView(R.id.download)
    ImageView mDownload;
    @BindView(R.id.download_info)
    TextView mDownloadInfoText;

    private AppListItemBean mAppListItemBean;
    private RectF mRectF;
    private Paint mPaint;
    private int mPercent;
    private boolean enableProgress = false;
    private DownloadInfo mDownloadInfo;

    public CircleDownloadView(Context context) {
        this(context, null);
    }

    public CircleDownloadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_download_progress, this);
        ButterKnife.bind(this, this);
        mRectF = new RectF();
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        //一般情况下自定义的ViewGroup不会绘制自己，除非给它设置背景，所以我们打开绘制自定义ViewGroup的开关
        setWillNotDraw(false);

    }

    /**
     * 同步app下载状态 同步qq音乐的
     *
     * @param appListItemBean
     */
    public void syncState(AppListItemBean appListItemBean) {
        //由于ListView的复用,CircleDownloadView已经可能监听了apk下载
        //如果CircleDownloadView被使用,它是回收回来的view,那么mDownloadInfo不为空
        if (mDownloadInfo != null) {
            //说明这是一个回收回来的CircleDownloadView mDownloadInfo是原来的apk downloadInfo
            //CircleDownloadView要移除以前的观察者
            DownloadManager.getInstance().removeObserver(mDownloadInfo.getPackageName());//人人
            //还残留以前的几个排队的runnable没有跑
        }

        mAppListItemBean =  appListItemBean;
        //初始化下载信息
        mDownloadInfo = DownloadManager.getInstance().initDownloadInfo(getContext(),
                appListItemBean.getPackageName(),
                appListItemBean.getSize(),
                appListItemBean.getDownloadUrl());
        //观察DownloadManager状态变化
        DownloadManager.getInstance().addObserver(appListItemBean.getPackageName(), this);

        //根据下载信息刷新界面
        updateStatus(mDownloadInfo);
    }

    private void updateStatus(DownloadInfo downloadInfo) {
        //过滤掉几个残留的runnable
        if (!downloadInfo.getPackageName().equals(mDownloadInfo.getPackageName())) {
            return;
        }

        switch (downloadInfo.getStatus()) {
            case DownloadManager.STATE_INSTALLED:
                //显示文本打开
                mDownloadInfoText.setText(getResources().getString(R.string.open));
                mDownload.setImageResource(R.drawable.ic_install);
                break;
            case DownloadManager.STATE_DOWNLOADED:
                mDownloadInfoText.setText(getResources().getString(R.string.install));
                mDownload.setImageResource(R.drawable.ic_install);
                break;
            case DownloadManager.STATE_UN_DOWNLOAD:
                clearProgress();//清除进度
                mDownloadInfoText.setText(getResources().getString(R.string.download));
                mDownload.setImageResource(R.drawable.ic_download);
                break;
            case DownloadManager.STATE_WAITING:
                //图片是x 文本是"等待"
                mDownload.setImageResource(R.drawable.ic_cancel);
                mDownloadInfoText.setText(getResources().getString(R.string.waiting));
                break;
            case DownloadManager.STATE_DOWNLOADING:
                //清除进度条
                clearProgress();
                //圆形进度条
                //暂停图片
                mDownload.setImageResource(R.drawable.ic_pause);
                //文本显示进度
                mPercent = (int) (downloadInfo.getProgress() * 1.0f / downloadInfo.getSize() * 100);
                String percentString = mPercent + "%";
                mDownloadInfoText.setText(percentString);
                //触发重新绘制 --> onDraw
                invalidate();
                break;
            case DownloadManager.STATE_PAUSE:
                //图片向下箭头
                //文本是继续
                mDownload.setImageResource(R.drawable.ic_download);
                mDownloadInfoText.setText(getResources().getString(R.string.continue_download));
                break;
            case DownloadManager.STATE_FAILED:
                //文本是重试， 图片是重试图片
                mDownload.setImageResource(R.drawable.ic_redownload);
                mDownloadInfoText.setText(getResources().getString(R.string.retry));
                break;
        }
    }

    /**
     * 清除进度条
     */
    private void clearProgress() {
        enableProgress = false;
        invalidate();
    }

    @OnClick(R.id.download)
    public void onViewClicked() {
        //点击处理交给DownloadManager处理
        DownloadManager.getInstance().handleDownloadAction(getContext(), mAppListItemBean.getPackageName());
    }

    @Override
    public void update(Observable o, Object arg) {
        final DownloadInfo downloadInfo = (DownloadInfo) arg;
        //在主线程更新状态,并不一定马上执行更新,可能还有runnable在排队,runnable其实是更新上一个apk
        post(new Runnable() {
            @Override
            public void run() {
                updateStatus(downloadInfo);
            }
        });
    }

    /**
     * 实现圆形进度条的绘制
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        //设置矩形的边界,矩形边界比ImageView要大
        int left = mDownload.getLeft() - 3;
        int top = mDownload.getTop() - 3;
        int right = mDownload.getRight() + 3;
        int bottom = mDownload.getBottom() + 3;
        mRectF.set(left, top, right, bottom);
        int startAngle = -90;
        int sweepAngle = (int) (mPercent * 1.0f / 100 * 360);
        boolean userCenter = false;
        canvas.drawArc(mRectF,startAngle, sweepAngle, userCenter, mPaint);

    }
}
