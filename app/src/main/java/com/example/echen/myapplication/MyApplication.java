package com.example.echen.myapplication;

import android.app.Application;

/**
 * Created by echen on 2015/1/14.
 */
public class MyApplication extends Application {
    private static MyApplication singleton;

    public static MyApplication getInstance(){
        return singleton;
    }

    @Override
    public final void onCreate() {
        super.onCreate();
        singleton = this;
    }
}
