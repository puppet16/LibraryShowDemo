package com.luck.libraryshowdemo.base;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.luck.libraryshowdemo.R;
import com.luck.libraryshowdemo.widget.GestureViewGroup;

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
    private GestureViewGroup mGestureView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        if (hasCustomSlide()) {
            mActivity.overridePendingTransition(R.anim.slide_right_in, 0);
            mActivity.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mGestureView = new GestureViewGroup(mActivity);
            mGestureView.setGestureViewGroupGoneListener(new GestureViewGroup.GestureViewGroupGoneListener() {
                @Override
                public void onFinish() {
                    mActivity.finish(); // 界面滑动消失后，销毁 Activity；
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (hasCustomSlide()) {
            FrameLayout decorView = (FrameLayout) mActivity.getWindow().getDecorView();
            View decorView_child = decorView.getChildAt(0);

            // 使用 GestureViewGroup 封装 DecorView 中的内容;
            if (!(decorView_child instanceof GestureViewGroup)) {
                decorView.removeAllViews();
                decorView_child.setBackgroundColor(Color.WHITE);
                mGestureView.addView(decorView_child);

                decorView.addView(mGestureView);
            }
        }
    }

    @Override
    public void finish() {
        super.finish();

        if (hasCustomSlide()) {
            mActivity.overridePendingTransition(0, R.anim.slide_right_out);
        }
    }

    protected boolean hasCustomSlide() {
        return true;
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
