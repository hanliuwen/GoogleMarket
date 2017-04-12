package com.example.administrator.googleplaydemo.utils;

/**
 * Created by Administrator on 2017/4/3.
 */

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 代理线程池的操作
 */
public class ThreadPoolProxy {

    private static ThreadPoolProxy sThreadPoolProxy;

    private ThreadPoolExecutor mThreadPoolExecutor;

    public ThreadPoolProxy() {
        //创建一个线程池
        mThreadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
    }

    //单例模式代理
    public static ThreadPoolProxy getInstance() {
        if (sThreadPoolProxy == null) {
            synchronized (ThreadPoolProxy.class) {
                if (sThreadPoolProxy == null) {
                    sThreadPoolProxy = new ThreadPoolProxy();
                }
            }
        }
        return sThreadPoolProxy;
    }

    /**
     * 执行一个任务
     */
    public void execute(Runnable runnable) {
        mThreadPoolExecutor.execute(runnable);
    }

    /**
     * 移除一个任务
     */
    public void remove(Runnable runnable) {
        mThreadPoolExecutor.remove(runnable);
    }
}
