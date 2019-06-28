package com.luck.libraryshowdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;

import com.luck.libraryshowdemo.R;
import com.luck.libraryshowdemo.base.BaseActivity;
import com.luck.libraryshowdemo.utils.LanguageUtils;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private SparseArray<Class<? extends BaseActivity>> mActivityArray = new SparseArray<Class<? extends BaseActivity>>() {
        {
            put(R.id.btnExcelPanel, ExcelPanelActivity.class);
            put(R.id.btnFlowLayout, FlowLayoutActivity.class);
            put(R.id.btnPhoneRegex, PhoneRegexActivity.class);
            put(R.id.btnStyleableToast, StyleableToastActivity.class);
            put(R.id.btnSwitchIcon, SwitchIconActivity.class);
            put(R.id.btnToasty, ToastyActivity.class);
            put(R.id.btnLanguageChange, LanguageActivity.class);
        }
    };

    @Override
    protected void initPage() {

    }

    @Override
    protected int getPageLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.btnExcelPanel, R.id.btnFlowLayout, R.id.btnPhoneRegex, R.id.btnStyleableToast,
            R.id.btnSwitchIcon, R.id.btnToasty, R.id.btnLanguageChange})
    public void onClick(View v) {
        startActivity(new Intent(mActivity, mActivityArray.get(v.getId())));
    }
}
