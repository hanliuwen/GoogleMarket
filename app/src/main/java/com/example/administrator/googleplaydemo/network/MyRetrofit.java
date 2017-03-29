package com.example.administrator.googleplaydemo.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/3/27.
 */

public class MyRetrofit {

    private static final String BASE_URL = "http://10.0.2.2:8080/GooglePlayServer/";
    //设置宽大处理畸形的json
    private Gson mGson = new GsonBuilder().setLenient().create();
    private final Api mApi;

    private static MyRetrofit mMyRetrofit;

    //懒汉式 单例模式
    public static MyRetrofit getInstance() {
        if (mMyRetrofit == null) {
            synchronized (MyRetrofit.class) {
                if (mMyRetrofit == null) {
                    mMyRetrofit = new MyRetrofit();
                }
            }
        }
        return mMyRetrofit;
    }

    private MyRetrofit() {
        //使用Retrofit来实现Api接口 需要配置gson转换器
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .build();

        mApi = retrofit.create(Api.class);
    }

    /**
     * 暴露api，让外界调用发送网络请求
     */
    public Api getApi() {
        return mApi;
    }
}
