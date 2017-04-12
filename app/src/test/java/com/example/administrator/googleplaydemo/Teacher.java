package com.example.administrator.googleplaydemo;

import java.util.Observable;

/**
 * Created by Administrator on 2017/4/2.
 * 被观察者
 */

public class Teacher extends Observable {

    public void publishMessage() {
        //设置发生的change
        setChanged();
        notifyObservers("放假不解释");//通知所有的观察者更新
    }
}
