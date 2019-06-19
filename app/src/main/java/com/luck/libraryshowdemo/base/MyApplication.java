package com.luck.libraryshowdemo.base;

import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;

/*************************************************************************************
 * Module Name:
 * Description:
 * Author: 李桐桐
 * Date:   2019/6/19
 *************************************************************************************/
public class MyApplication extends Application {
    private static WeakReference<Context> mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = new WeakReference<>(getApplicationContext());
    }

    public static Context getContext() {
        return mContext.get();
    }
}
