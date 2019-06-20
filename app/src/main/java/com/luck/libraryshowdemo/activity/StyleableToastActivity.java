package com.luck.libraryshowdemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.luck.libraryshowdemo.R;
import com.luck.libraryshowdemo.base.BaseActivity;
import com.muddzdev.styleabletoast.StyleableToast;

import butterknife.OnClick;
import butterknife.OnLongClick;

public class StyleableToastActivity extends BaseActivity {

    private String toastMsg = "测试Test123";
    private int redColor = Color.parseColor("#FF5A5F");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_styleabletoast);

    }

    @OnClick({R.id.styleable_b1, R.id.styleable_b2,R.id.styleable_b3,
            R.id.styleable_b4, R.id.styleable_b5,R.id.styleable_b6,
            R.id.styleable_b7, R.id.styleable_b8,R.id.styleable_b9, R.id.styleable_b10})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.styleable_b1:
                new StyleableToast.Builder(this)
                        .text(toastMsg)
                        .backgroundColor(redColor)
                        .show();
                break;
            case R.id.styleable_b2:
                new StyleableToast.Builder(this)
                        .text(toastMsg)
                        .textColor(redColor)
                        .show();
                break;
            case R.id.styleable_b3:
                new StyleableToast.Builder(this)
                        .text(toastMsg)
                        .textColor(redColor)
                        .textBold()
                        .show();
                break;
            case R.id.styleable_b4:
                new StyleableToast.Builder(this)
                        .text(toastMsg)
                        .font(R.font.dosis)
                        .show();
                break;
            case R.id.styleable_b5:
                new StyleableToast.Builder(this)
                        .text(toastMsg)
                        .cornerRadius(5)
                        .show();
                break;
            case R.id.styleable_b6:
                new StyleableToast.Builder(this)
                        .text(toastMsg)
                        .iconStart(R.drawable.ic_styleabletoast_icon)
                        .show();
                break;
            case R.id.styleable_b7:
                new StyleableToast.Builder(this)
                        .text(toastMsg)
                        .iconEnd(R.drawable.ic_styleabletoast_icon)
                        .show();
                break;
            case R.id.styleable_b8:
                new StyleableToast.Builder(this)
                        .text(toastMsg)
                        .iconStart(R.drawable.ic_styleabletoast_icon)
                        .iconEnd(R.drawable.ic_styleabletoast_icon)
                        .show();
                break;
            case R.id.styleable_b9:
                new StyleableToast.Builder(this)
                        .text(toastMsg)
                        .stroke(2, redColor)
                        .show();
                break;
            case R.id.styleable_b10:
                new StyleableToast.Builder(this)
                        .text(toastMsg)
                        .stroke(2, Color.CYAN)
                        .backgroundColor(Color.BLACK)
                        .solidBackground()
                        .textColor(Color.YELLOW)
                        .textBold()
                        .font(R.font.dosis)
                        .iconStart(R.drawable.ic_styleabletoast_icon)
                        .iconEnd(R.drawable.ic_styleabletoast_icon)
                        .cornerRadius(12)
                        .textSize(18)
                        .show();
                break;
        }
    }
    @OnLongClick({R.id.styleable_b1, R.id.styleable_b2,R.id.styleable_b3,
            R.id.styleable_b4, R.id.styleable_b5,R.id.styleable_b6,
            R.id.styleable_b7, R.id.styleable_b8,R.id.styleable_b9, R.id.styleable_b10})
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.styleable_b1:
                StyleableToast.makeText(this, toastMsg, R.style.ColoredBackground).show();
                break;
            case R.id.styleable_b2:
                StyleableToast.makeText(this, toastMsg, R.style.ColoredText).show();
                break;
            case R.id.styleable_b3:
                StyleableToast.makeText(this, toastMsg, R.style.ColoredBoldText).show();
                break;
            case R.id.styleable_b4:
                StyleableToast.makeText(this, toastMsg, R.style.CustomFont).show();
                break;
            case R.id.styleable_b5:
                StyleableToast.makeText(this, toastMsg, R.style.CornerRadius5Dp).show();
                break;
            case R.id.styleable_b6:
                StyleableToast.makeText(this, toastMsg, R.style.IconStart).show();
                break;
            case R.id.styleable_b7:
                StyleableToast.makeText(this, toastMsg, R.style.IconEnd).show();
                break;
            case R.id.styleable_b8:
                StyleableToast.makeText(this, toastMsg, R.style.IconStartEnd).show();
                break;
            case R.id.styleable_b9:
                StyleableToast.makeText(this, toastMsg, R.style.ColoredStroke).show();
                break;
            case R.id.styleable_b10:
                StyleableToast.makeText(this, toastMsg, R.style.AllStyles).show();
                break;
        }
        return true;
    }
}
