package com.magical.library.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Project: TShow
 * FileName: StringUtils.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:56 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:56 PM
 * Remark:
 */
public class StringUtils {

    public static String encodeUTF(String str) {
        if (isNotEmpty(str)) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return str;
            }
        } else {
            return "";
        }
    }

    public static String decodeUTF(String str) {
        if (isNotEmpty(str)) {
            try {
                return URLDecoder.decode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return str;
            }
        } else {
            return "";
        }
    }

    public static boolean isEmpty(Object str) {
        return null == str || str.toString().length() == 0 ||
                str.toString().trim().length() == 0 ||
                "None".equalsIgnoreCase(str.toString()) ||
                "null".equalsIgnoreCase(str.toString()) ||
                "(null)".equalsIgnoreCase(str.toString());
    }

    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }


    public static boolean startWithHttp(Object str) {
        return isNotEmpty(str) &&
                (str.toString().toLowerCase().startsWith("http://") ||
                        str.toString().toLowerCase().startsWith("https://") ||
                        str.toString().toLowerCase().startsWith("ftp://"));
    }

    /**
     * 切分url中的参数为map
     */
    public static Map<String, String> parseParams(String url) {
        Map<String, String> result = new LinkedHashMap();
        String[] params = url.replaceAll("(^[^\\?]*\\?)|(#[^#]*$)", "")
                .split("&");
        for (String keyValue : params) {
            String[] param = keyValue.split("=");
            if (param.length >= 1) {
                result.put(param[0], param.length >= 2
                        ? StringUtils.decodeUTF(param[1])
                        : "");
            }
        }
        return result;
    }

    /**
     * 返回url中的参数值
     */
    public static String getUrlParamValue(String url, String key) {
        Map<String, String> map = parseParams(url);
        return map.containsKey(key) ? map.get(key) : "";
    }
}
