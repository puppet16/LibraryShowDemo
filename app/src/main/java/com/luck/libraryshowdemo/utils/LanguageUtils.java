package com.luck.libraryshowdemo.utils;

/*************************************************************************************
 * Module Name:
 * Description:
 * Author: 李桐桐
 * Date:   2019/6/28
 *************************************************************************************/

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.provider.SyncStateContract;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * Created by dumingwei on 2018/5/31 0031.
 */
public class LanguageUtils {

    private static final String TAG = "LanguageUtil";
    private static final String LOCAL_LANGUAGE = "local_language";
    private static volatile LanguageUtils mInstance;


    private static Context mContext;
    private static Resources mResources;
    private static Context configurationContext;

    /**
     * 语言类型的枚举
     */
    public enum Language {
        AUTO, CHINESE, ENGLISH
    }

    public LanguageUtils() {
        mContext = ApplicationUtils.getApp().getApplicationContext();
        mResources = mContext.getResources();
    }

    public static LanguageUtils getInstance() {
        if (null == mInstance) {
            synchronized (LanguageUtils.class) {
                if (null == mInstance) {
                    mInstance = new LanguageUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * 适配7.0以上系统，在基类里的attachBaseContext方法中调用
     *
     * @param context
     * @return
     */
    public Context attachBaseContext(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && getConfigurationContext() != null) { // 7.0需要使用createConfigurationContext处理
            return getConfigurationContext();
        } else {
            return context;
        }
    }

    public void setLocalLanguageToShow() {
        String str = getLocalLanguageString();
        if (Language.AUTO.toString().equals(str)) {
            changeLanguage(Language.AUTO);
        } else if (Language.CHINESE.toString().equals(str)) {
            changeLanguage(Language.CHINESE);
        } else if (Language.ENGLISH.toString().equals(str)) {
            changeLanguage(Language.ENGLISH);
        }
    }

    /**
     * 切换语言
     */
    public void changeLanguage(Language language) {
        Configuration configuration = mResources.getConfiguration();
        DisplayMetrics displayMetrics = mResources.getDisplayMetrics();
        String string = language.toString();
        if ("".equals(string)) {
            throw new RuntimeException("语言类型为空");
        }
        setLocalLanguage(language);
        if (Language.AUTO.toString().equals(string)) {
            setSystemDefaultLanguage();
            return;
        }
        Locale locale = getSetLocale(string);// getSetLocale方法是获取新设置的语言
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.setLocale(locale);
            configuration.setLocales(new LocaleList(locale));
            configurationContext = mContext.createConfigurationContext(configuration);
        } else {
            configuration.locale = locale;
            mResources.updateConfiguration(configuration, displayMetrics);
        }
    }

    /**
     * 根据本地存的类型返回语言
     *
     * @return 默认为简体中文
     */
    private Locale getSetLocale(String language) {
        if (Language.CHINESE.toString().equals(language)) {
            return Locale.SIMPLIFIED_CHINESE;
        } else if (Language.ENGLISH.toString().equals(language)) {
            return Locale.ENGLISH;
        }
        return Locale.SIMPLIFIED_CHINESE;
    }

    /**
     * 获取语言的字符串
     *
     * @return 语言类型 默认为自动
     */
    private String getLocalLanguageString() {
        return PreferencesUtils.getString(LOCAL_LANGUAGE, Language.AUTO.toString());
    }

    /**
     * 将设置的语言保存到本地
     *
     * @param language 语言类型
     */
    public void setLocalLanguage(Language language) {
        if (language == null) {
            // 设置 null 则为默认语言
            PreferencesUtils.remove(LOCAL_LANGUAGE, true);
            return;
        }
        PreferencesUtils.put(LOCAL_LANGUAGE, language.toString(), true);
    }

    /**
     * 获取系统当前默认的语言,并根据系统的语言设置app的语言 ,
     * 如果 系统语言在当前app中包含了 并且与当前app中设置的app的语言不相同时 返回true 并更改配置 否则返回false
     *
     * @return
     */
    private boolean setSystemDefaultLanguage() {
        Locale defaultLanguage;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            defaultLanguage = LocaleList.getDefault().get(0);
        } else {
            defaultLanguage = Locale.getDefault();
        }
        String language = defaultLanguage.getLanguage();
        if (TextUtils.equals(language, getLocalLanguageString())) {
            return false;
        }
        Configuration configuration = mResources.getConfiguration();
        DisplayMetrics displayMetrics = mResources.getDisplayMetrics();
        configuration.locale = defaultLanguage;
        mResources.updateConfiguration(configuration, displayMetrics);
        return true;
    }

    /**
     * 获取Value资源下String文件中定义好的文字
     * 特别需要注意的是 在程序需要进行国际化开发的时候 这个string 一定要是通过 Resources.getString()的方式获取
     * 否则获取的字段仅仅是默认语言下的String字段,无法实现语言切换
     *
     * @param id String中字段的id
     * @return String中的字段
     */
    public static String getString(int id) {
        return mResources.getString(id);
    }

    private static Context getConfigurationContext() {
        return configurationContext;
    }

    /**
     * 判断当前程序的语言是不是中文,包含选择自动时的语言
     */
    public boolean isChinese() {
        String languageString = getLocalLanguageString();
        if (TextUtils.isEmpty(languageString)) {
            return isChineseLocal();//说明没有修改过语言
        }
        return Language.CHINESE.toString().equals(languageString);//修改过的话以修改的为准
    }

    private boolean isChineseLocal() {
        Locale locale = ApplicationUtils.getApp().getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        return language.endsWith("zh");
    }

    /**
     * 当前存储在本地的类型是自动不
     *
     * @return
     */
    public boolean isAuto() {
        return Language.AUTO.toString().equals(getLocalLanguageString());
    }

    /**
     * 当前存储在本地的类型是简体中文么
     *
     * @return
     */
    public boolean isSimplifiedChinese() {
        return Language.CHINESE.toString().equals(getLocalLanguageString());
    }

    /**
     * 当前存储在本地的类型是英文吗
     *
     * @return
     */
    public boolean isEnglish() {
        return Language.ENGLISH.toString().equals(getLocalLanguageString());
    }
}
