package com.luck.libraryshowdemo.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*************************************************************************************
 * Module Name:
 * Description: 手机号检验合法管理类
 * Author: 李桐桐
 * Date:   2019/6/19
 *************************************************************************************/
public class PhoneRegexManager {

    private static volatile PhoneRegexManager mInstance;
    //匹配所有号码（手机卡 + 数据卡 + 上网卡）
    private final String regexAllCard = "^(?:\\+?86)?1(?:3\\d{3}|5[^4\\D]\\d{2}|8\\d{3}|7(?:[01356789]\\d{2}|4(?:0\\d|1[0-2]|9\\d))|9[189]\\d{2}|6[567]\\d{2}|4(?:[14]0\\d{3}|[68]\\d{4}|[579]\\d{2}))\\d{6}$";
    //匹配所有支持短信功能的号码（手机卡 + 上网卡）
    private final String regexPhoneOfMessage = "^(?:\\+?86)?1(?:3\\d{3}|5[^4\\D]\\d{2}|8\\d{3}|7(?:[01356789]\\d{2}|4(?:0\\d|1[0-2]|9\\d))|9[189]\\d{2}|6[567]\\d{2}|4[579]\\d{2})\\d{6}$";

    //-----------手机卡----------------
    //所有手机卡
    private final String regexPhone = "^(?:\\+?86)?1(?:3\\d{3}|5[^4\\D]\\d{2}|8\\d{3}|7(?:[35678]\\d{2}|4(?:0\\d|1[0-2]|9\\d))|9[189]\\d{2}|66\\d{2})\\d{6}$";
    //移动卡
    private final String regexPhoneMobile = "^(?:\\+?86)?1(?:3\\d{3}|5[^4\\D]\\d{2}|8\\d{3}|7(?:[01356789]\\d{2}|4(?:0\\d|1[0-2]|9\\d))|9[189]\\d{2}|6[567]\\d{2}|4(?:[14]0\\d{3}|[68]\\d{4}|[579]\\d{2}))\\d{6}$";
    //联通卡
    private final String regexPhoneUnicom = "^(?:\\+?86)?1(?:3[0-2]|[578][56]|66)\\d{8}$";
    //电信卡
    private final String regexPhoneTelecom = "^(?:\\+?86)?1(?:3(?:3\\d|49)\\d|53\\d{2}|8[019]\\d{2}|7(?:[37]\\d{2}|40[0-5])|9[19]\\d{2})\\d{6}$";
    //---------------虚拟运营商--------------
    //匹配所有
    private final String regexVirtualPhone = "^(?:\\+?86)?1(?:7[01]|6[57])\\d{8}$";
    //移动卡
    private final String regexVirtualPhoneMobile = "^(?:\\+?86)?1(?:65\\d|70[356])\\d{7}$";
    //联通卡
    private final String regexVirtualPhoneUnicom = "^(?:\\+?86)?1(?:70[4789]|71\\d|67\\d)\\d{7}$";
    //电信卡
    private final String regexVirtualPhoneTelecom = "^(?:\\+?86)?170[0-2]\\d{7}$";

    public static PhoneRegexManager getInstance() {
        if (mInstance == null) {
            synchronized (PhoneRegexManager.class) {
                if (mInstance == null) {
                    mInstance = new PhoneRegexManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 所有号码
     *
     * @param phone
     * @return
     */
    public boolean checkAll(String phone) {
        return checkPhone(phone, regexAllCard);
    }

    /**
     * 所有支持短信的号码
     *
     * @param phone
     * @return
     */
    public boolean checkPhoneOfMessage(String phone) {
        return checkPhone(phone, regexPhoneOfMessage);
    }
    /**
     * 所有手机卡
     *
     * @param phone
     * @return
     */
    public boolean checkPhoneAll(String phone) {
        return checkPhone(phone, regexPhone);
    }

    /**
     * 移动手机卡
     *
     * @param phone
     * @return
     */
    public boolean checkPhoneMobile(String phone) {
        return checkPhone(phone, regexPhoneMobile);
    }

    /**
     * 联通手机卡
     *
     * @param phone
     * @return
     */
    public boolean checkPhoneUnicom(String phone) {
        return checkPhone(phone, regexPhoneUnicom);
    }

    /**
     * 电信手机卡
     *
     * @param phone
     * @return
     */
    public boolean checkPhoneTelecom(String phone) {
        return checkPhone(phone, regexPhoneTelecom);
    }

    /**
     * 所有虚拟运营卡
     *
     * @param phone
     * @return
     */
    public boolean checkVirtualPhone(String phone) {
        return checkPhone(phone, regexVirtualPhone);
    }

    /**
     * 虚拟移动手机卡
     *
     * @param phone
     * @return
     */
    public boolean checkVirtualPhoneMobile(String phone) {
        return checkPhone(phone, regexVirtualPhoneMobile);
    }

    /**
     * 虚拟联通手机卡
     *
     * @param phone
     * @return
     */
    public boolean checkVirtualPhoneUnicom(String phone) {
        return checkPhone(phone, regexVirtualPhoneUnicom);
    }

    /**
     * 虚拟电信手机卡
     *
     * @param phone
     * @return
     */
    public boolean checkVirtualPhoneTelecom(String phone) {
        return checkPhone(phone, regexVirtualPhoneTelecom);
    }

    /**
     * 根据正测检验号码是否合法
     *
     * @param phone 手机号
     * @param regex 正则表达式
     * @return true 合法；false 不合法
     */
    private boolean checkPhone(String phone, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
