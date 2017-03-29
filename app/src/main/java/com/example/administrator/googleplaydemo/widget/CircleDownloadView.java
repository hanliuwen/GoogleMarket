package com.example.administrator.googleplaydemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.administrator.googleplaydemo.R;

/**
 * Created by Administrator on 2017/3/29.
 */

public class CircleDownloadView extends RelativeLayout {

    public CircleDownloadView(Context context) {
        this(context,null);
    }

    public CircleDownloadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_download_progress, this);
    }
}
