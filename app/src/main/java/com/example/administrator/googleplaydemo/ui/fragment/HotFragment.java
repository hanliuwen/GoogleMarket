package com.example.administrator.googleplaydemo.ui.fragment;


import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.googleplaydemo.R;
import com.example.administrator.googleplaydemo.network.Api;
import com.example.administrator.googleplaydemo.network.MyRetrofit;
import com.example.administrator.googleplaydemo.widget.FlowLayout;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/3/26.
 */
public class HotFragment extends BaseFragment {

    private static final String TAG = "HotFragment";
    private List<String> mDataList;
    private GradientDrawable normalDrawable;

    @Override
    protected void startLoadData() {
        //获取api
        Api api = MyRetrofit.getInstance().getApi();
        //获取网络请求call
        Call<List<String>> listCall = api.listHot();

        listCall.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                Toast.makeText(getContext(),"网络成功", Toast.LENGTH_SHORT).show();
                mDataList = response.body();
                Log.d(TAG, "onResponse: " + response.body().toString());
                OnDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(getContext(), "网络失败", Toast.LENGTH_SHORT).show();
                onDataLoadedFailed();
            }
        });
    }

    /**
     *
     * @return 热门界面的视图
     */
    @Override
    protected View onCreateContentView() {
        ScrollView scrollView = new ScrollView(getContext());
        //流式布局
        FlowLayout fl = new FlowLayout(getContext());
        int padding = getResources().getDimensionPixelOffset(R.dimen.padding);
        fl.setPadding(padding,padding,padding,padding);

        //给fl添加应有的孩子
        for (int i = 0; i < mDataList.size(); i++) {
            final String data = mDataList.get(i);
            TextView tv = getTextView(padding,data);
            //创建selector
            StateListDrawable stateListDrawable = getStateListDrawable();
            tv.setBackgroundDrawable(stateListDrawable);
            //设置tv可以点击
            tv.setClickable(true);
            //给textView设置点击事件
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), data, Toast.LENGTH_SHORT).show();
                }
            });
            fl.addView(tv);
        }
        scrollView.addView(fl);

        return scrollView;
    }

    private StateListDrawable getStateListDrawable() {
        //创建一个shape
        normalDrawable = new GradientDrawable();
        //设置圆角
        normalDrawable.setCornerRadius(8);
        //随机产生颜色
        int argb = getArgb();
        normalDrawable.setColor(argb);
        //创建selector
        StateListDrawable stateListDrawable = new StateListDrawable();
        //添加摁下去的状态的drawable
        GradientDrawable pressedDrawable = new GradientDrawable();
        //设置圆角
        pressedDrawable.setCornerRadius(8);
        pressedDrawable.setColor(Color.DKGRAY);
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed},pressedDrawable);
        //添加正常状态drawable, 空数组表示其他状态都是normalDrawable
        stateListDrawable.addState(new int[]{},normalDrawable);
        return stateListDrawable;
    }

    private TextView getTextView(int padding, String data) {
        TextView textView = new TextView(getContext());
        textView.setText(data);
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(padding,padding,padding,padding);
        return textView;
    }

    public int getArgb() {
        int alpha = 255;
        int red = 30 + new Random().nextInt(200);
        int green = 30 + new Random().nextInt(200);
        int blue = 30 + new Random().nextInt(200);
        return Color.argb(alpha,red,green,blue);
    }
}
