package com.luck.libraryshowdemo.activity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.luck.libraryshowdemo.R;
import com.luck.libraryshowdemo.base.BaseActivity;
import com.nex3z.flowlayout.FlowLayout;

import butterknife.BindView;

public class FlowLayoutActivity extends BaseActivity {

    private String mContent = "There are moments in life when you miss someone so much that you just want to pick them from your dreams and hug them for real! Dream what you want to dream; go where you want to go; be what you want to be, because you have only one life and one chance to do all the things you want to do.\n" +
            " May you have enough happiness to make you sweet, enough trials to make you strong, enough sorrow to keep you human, enough hope to make you happy? Always put yourself in others’shoes. If you feel that it hurts you, it probably hurts the other person, too.";

    @BindView(R.id.flow)
    FlowLayout mFlowLayout;

    @Override
    protected void initPage() {
        initData();
    }

    @Override
    protected int getPageLayoutId() {
        return R.layout.activity_flowlayout;
    }

    private void initData() {
        String[] contentArray = mContent.split(" ");
        for (String s : contentArray) {
            TextView textView = new TextView(mActivity);
            textView.setPadding(dpToPx(8), dpToPx(4), dpToPx(8), dpToPx(4));
            textView.setBackgroundResource(R.drawable.bg_flowlayout_textview_selector);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(dpToPx(8), dpToPx(4), dpToPx(8), dpToPx(4));
            textView.setLayoutParams(params);
            textView.setText(s);
            textView.setOnClickListener(v -> {
                Toast.makeText(mActivity, "点击了："+((TextView)v).getText().toString(),Toast.LENGTH_SHORT).show();
            });
            mFlowLayout.addView(textView);
        }
    }

    private int dpToPx(float dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
