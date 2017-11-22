package com.magical.library.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Project: TShow
 * FileName: RegularUtils.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:50 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:50 PM
 * Remark:
 */
public class RegularUtils {

    /**
     * Don't let anyone instantiate this class.
     */
    private RegularUtils() {
        throw new Error("Do not need instantiate!");
    }


    /**
     * 邮箱检测
     *
     * @param email 可能是Email的字符串
     * @return 是否是Email
     */
    public static boolean isEmail(String email) {
        Pattern pattern = Pattern
                .compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    /**
     * 邮箱验证
     *
     * @param data 可能是Email的字符串
     * @return 是否是Email
     */
    public static boolean isEmail2(String data) {
        String expr = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return data.matches(expr);
    }


    /**
     * 移动手机号码验证
     *
     * @param str 可能是手机号码字符串
     * @return 是否是手机号码
     */
    public static boolean isPhone(String str) {
        String telRegex = "[1][3578]\\d{9}";//
        // "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(str)) {
            return false;
        } else {
            return str.matches(telRegex);
        }
    }


    /**
     * 只含字母和数字
     *
     * @param data 可能只包含字母和数字的字符串
     * @return 是否只包含字母和数字
     */
    public static boolean isNumberLetter(String data) {
        String expr = "^[A-Za-z0-9]+$";
        return data.matches(expr);
    }


    /**
     * 只含数字
     *
     * @param data 可能只包含数字的字符串
     * @return 是否只包含数字
     */
    public static boolean isNumber(String data) {
        String expr = "^[0-9]+$";
        return data.matches(expr);
    }


    /**
     * 只含字母
     *
     * @param data 可能只包含字母的字符串
     * @return 是否只包含字母
     */
    public static boolean isLetter(String data) {
        String expr = "^[A-Za-z]+$";
        return data.matches(expr);
    }


    /**
     * 只是中文
     *
     * @param data 可能是中文的字符串
     * @return 是否只是中文
     */
    public static boolean isChinese(String data) {
        String expr = "^[\u0391-\uFFE5]+$";
        return data.matches(expr);
    }


    /**
     * 包含中文
     *
     * @param data 可能包含中文的字符串
     * @return 是否包含中文
     */
    public static boolean isContainChinese(String data) {
        String chinese = "[\u0391-\uFFE5]";
        if (TextUtils.isEmpty(data)) {
            for (int i = 0; i < data.length(); i++) {
                String temp = data.substring(i, i + 1);
                boolean flag = temp.matches(chinese);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 小数点位数
     *
     * @param data   可能包含小数点的字符串
     * @param length 小数点后的长度
     * @return 是否小数点后有length位数字
     */
    public static boolean isDianWeiShu(String data, int length) {
        String expr = "^[1-9][0-9]+\\.[0-9]{" + length + "}$";
        return data.matches(expr);
    }


    /**
     * 身份证号码验证
     *
     * @param data 可能是身份证号码的字符串
     * @return 是否是身份证号码
     */
    public static boolean isCard(String data) {
        String expr = "^[0-9]{17}[0-9xX]$";
        return data.matches(expr);
    }


    /**
     * 邮政编码验证
     *
     * @param data 可能包含邮政编码的字符串
     * @return 是否是邮政编码
     */
    public static boolean isPostCode(String data) {
        String expr = "^[0-9]{6,10}";
        return data.matches(expr);
    }


    /**
     * 长度验证
     *
     * @param data   源字符串
     * @param length 期望长度
     * @return 是否是期望长度
     */
    public static boolean isLength(String data, int length) {

        return data != null && data.length() == length;
    }
}
