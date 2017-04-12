package com.example.administrator.googleplaydemo;

import java.util.Observable;
import java.util.Observer;


/**
 * Created by Administrator on 2017/4/2.
 * 观察者
 */

public class Student implements Observer {

    private static final String TAG = "Student";
    String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.print(name + "update: " + arg.toString());
    }
}
