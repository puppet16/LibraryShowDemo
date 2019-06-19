package com.luck.libraryshowdemo.base;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/*************************************************************************************
 * Module Name:
 * Description:
 * Author: 李桐桐
 * Date:   2019/6/19
 *************************************************************************************/
public class BaseActivity extends AppCompatActivity {
    protected Activity mActivity;
    public final String TAG = getClass().getSimpleName();
    protected Unbinder mBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mBinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBinder != null)
            mBinder.unbind();
    }
}
