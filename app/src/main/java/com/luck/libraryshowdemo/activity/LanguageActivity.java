package com.luck.libraryshowdemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.luck.libraryshowdemo.R;
import com.luck.libraryshowdemo.base.BaseActivity;
import com.luck.libraryshowdemo.event.LanguageChangedEvent;
import com.luck.libraryshowdemo.utils.LanguageUtils;
import com.luck.libraryshowdemo.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class LanguageActivity extends BaseActivity {
    @BindView(R.id.languageIvAuto)
    ImageView mIvAuto;
    @BindView(R.id.languageIvChinese)
    ImageView mIvChinese;
    @BindView(R.id.languageIvEnglish)
    ImageView mIvEnglish;

    @Override
    protected void initPage() {
        changeState();
    }

    @Override
    protected int getPageLayoutId() {
        return R.layout.activity_language;
    }

    @OnClick({R.id.languageFlAuto, R.id.languageFlChinese, R.id.languageFlEnglish})
    public void onClick(View v) {
        boolean isModify = false;
        switch (v.getId()) {
            case R.id.languageFlAuto:
                if (LanguageUtils.getInstance().isAuto()) {
                    Toasty.warning(mActivity, LanguageUtils.getString(R.string.language_now_is) + LanguageUtils.getString(R.string.language_auto)).show();
                    return;
                }
                isModify = true;
                LanguageUtils.getInstance().changeLanguage(LanguageUtils.Language.AUTO);
                break;
            case R.id.languageFlChinese:
                if (LanguageUtils.getInstance().isSimplifiedChinese()) {
                    Toasty.warning(mActivity, LanguageUtils.getString(R.string.language_now_is) + LanguageUtils.getString(R.string.language_chinese)).show();
                    return;
                }
                isModify = true;
                LanguageUtils.getInstance().changeLanguage(LanguageUtils.Language.CHINESE);

                break;
            case R.id.languageFlEnglish:
                if (LanguageUtils.getInstance().isEnglish()) {
                    Toasty.warning(mActivity, LanguageUtils.getString(R.string.language_now_is) + LanguageUtils.getString(R.string.language_english)).show();
                    return;
                }
                isModify = true;
                LanguageUtils.getInstance().changeLanguage(LanguageUtils.Language.ENGLISH);
                break;
        }
        if (isModify)
            EventBus.getDefault().post(new LanguageChangedEvent.updateLanguageEvent());
        LogUtils.dTag(TAG, "当前语言是中文么：" + LanguageUtils.getInstance().isChinese());
        changeState();
    }

    private void changeState() {
        mIvAuto.setVisibility(LanguageUtils.getInstance().isAuto() ? View.VISIBLE : View.GONE);
        mIvChinese.setVisibility(LanguageUtils.getInstance().isSimplifiedChinese() ? View.VISIBLE : View.GONE);
        mIvEnglish.setVisibility(LanguageUtils.getInstance().isEnglish() ? View.VISIBLE : View.GONE);
    }
}
