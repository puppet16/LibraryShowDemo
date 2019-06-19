package com.luck.libraryshowdemo.activity;

import android.os.Bundle;
import android.view.View;

import com.github.zagum.switchicon.SwitchIconView;
import com.luck.libraryshowdemo.R;
import com.luck.libraryshowdemo.base.BaseActivity;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.OnClick;

public class SwitchIconActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switchicon);
    }

    @OnClick({R.id.switchIconView1, R.id.switchIconView2, R.id.switchIconView3,
            R.id.switchIconView4, R.id.switchIconView5, R.id.switchIconView6, R.id.switchIconView7,
            R.id.switchIconView8, R.id.switchIconView9})
    public void onClick(SwitchIconView v) {
        v.switchState();
    }
}
