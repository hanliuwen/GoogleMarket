package com.example.administrator.googleplaydemo.network;

import com.example.administrator.googleplaydemo.bean.AppDetailBean;
import com.example.administrator.googleplaydemo.bean.AppListItemBean;
import com.example.administrator.googleplaydemo.bean.CategoryItemBean;
import com.example.administrator.googleplaydemo.bean.HomeBean;
import com.example.administrator.googleplaydemo.bean.SubjectItemBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/3/27.
 */

public interface Api {

    /**
     * 泛型T你想要解析后数据结构
     */
    @GET("hot")
    Call<List<String>> listHot();

    @GET("recommend")
    Call<List<String>> listRecommend();

    @GET("category")
    Call<List<CategoryItemBean>> listCategory();

    /**
     *
     * @param index 查询参数
     * @return
     */
    @GET("subject")
    Call<List<SubjectItemBean>> listSubject(@Query("index") int index);

    @GET("game")
    Call<List<AppListItemBean>> listGame(@Query("index")int index);

    @GET("app")
    Call<List<AppListItemBean>> listApp(@Query("index")int index);

    @GET("home")
    Call<HomeBean> listHome(@Query("index")int index);

    @GET("detail")
    Call<AppDetailBean> getAppDetail(@Query("packageName") String packageName);
}
