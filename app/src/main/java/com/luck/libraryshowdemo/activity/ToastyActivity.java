package com.luck.libraryshowdemo.activity;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.View;

import com.luck.libraryshowdemo.R;
import com.luck.libraryshowdemo.base.BaseActivity;

import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

import static android.graphics.Typeface.BOLD_ITALIC;

public class ToastyActivity extends BaseActivity {

    @Override
    protected void initPage() {

    }

    @Override
    protected int getPageLayoutId() {
        return R.layout.activity_toasty;
    }

    @OnClick({R.id.toasty_button_custom_config, R.id.toasty_button_error_toast, R.id.toasty_button_info_toast,
            R.id.toasty_button_info_toast_with_formatting, R.id.toasty_button_normal_toast_w_icon,
            R.id.toasty_button_normal_toast_wo_icon, R.id.toasty_button_success_toast,
            R.id.toasty_button_warning_toast, R.id.toasty_button_only_show_last})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toasty_button_custom_config:
                Toasty.Config.getInstance()
                        .setToastTypeface(Typeface.createFromAsset(getAssets(), "PCap Terminal.otf"))
                        .allowQueue(false)
                        .apply();
                Toasty.custom(mActivity, R.string.toasty_custom_message, getResources().getDrawable(R.drawable.ic_switch_icon_timer),
                        android.R.color.black, android.R.color.holo_green_light, Toasty.LENGTH_SHORT, true, true).show();
                Toasty.Config.reset(); // Use this if you want to use the configuration above only once
                break;
            case R.id.toasty_button_error_toast:
                Toasty.error(mActivity, R.string.toasty_error_message, Toasty.LENGTH_SHORT, true).show();

                break;
            case R.id.toasty_button_info_toast:
                Toasty.info(mActivity, R.string.toasty_info_message, Toasty.LENGTH_SHORT, true).show();

                break;
            case R.id.toasty_button_info_toast_with_formatting:
                Toasty.info(mActivity, getFormattedMessage()).show();

                break;
            case R.id.toasty_button_normal_toast_w_icon:
                Drawable icon = getResources().getDrawable(R.drawable.ic_toasty_face);
                Toasty.normal(mActivity, R.string.toasty_normal_message_with_icon, icon).show();
                break;
            case R.id.toasty_button_normal_toast_wo_icon:
                Toasty.normal(mActivity, R.string.toasty_normal_message_without_icon).show();

                break;
            case R.id.toasty_button_success_toast:
                Toasty.success(mActivity, R.string.toasty_success_message, Toasty.LENGTH_SHORT, true).show();

                break;
            case R.id.toasty_button_warning_toast:
                Toasty.warning(mActivity, R.string.toasty_warning_message, Toasty.LENGTH_SHORT, true).show();
                break;
            case R.id.toasty_button_only_show_last:
                Toasty.Config.getInstance().allowQueue(false).apply();
                break;
        }
    }

    private CharSequence getFormattedMessage() {
        final String prefix = "Formatted ";
        final String highlight = "bold italic";
        final String suffix = " text";
        SpannableStringBuilder ssb = new SpannableStringBuilder(prefix).append(highlight).append(suffix);
        int prefixLen = prefix.length();
        ssb.setSpan(new StyleSpan(BOLD_ITALIC),
                prefixLen, prefixLen + highlight.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ssb;
    }
}
