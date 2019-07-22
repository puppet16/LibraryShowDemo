package com.luck.libraryshowdemo.activity;

import android.view.View;
import android.widget.TextView;

import com.facebook.device.yearclass.YearClass;
import com.luck.libraryshowdemo.R;
import com.luck.libraryshowdemo.base.BaseActivity;
import com.luck.libraryshowdemo.utils.ApplicationUtils;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/*************************************************************************************
 * Module Name:
 * Description:
 * Author: 李桐桐
 * Date:   2019/7/22
 *************************************************************************************/
public class DecideYearActivity extends BaseActivity {

    @BindView(R.id.decideYearTv)
    TextView mTvResult;

    @Override
    protected void initPage() {

    }

    @Override
    protected int getPageLayoutId() {
        return R.layout.activity_decide_year;
    }

    @OnClick(R.id.decideYearBtn)
    public void onClick(View v) {
        int result = YearClass.get(ApplicationUtils.getApp());
        mTvResult.setText(String.format(Locale.CHINA, "当前设置年份为：%d", result));
        if (result >= 2013) {
            //2013版本已经迭代到4.2了
            // Do advanced animation
        } else if (result > 2010) {
            // Do simple animation
            //2011版本已经迭代到2.2了
        } else {
            // Phone too slow, don't do any animations
        }
    }
}
