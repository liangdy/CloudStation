package com.cloud.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Project: CloudStation
 * FileName: JsonUtils.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/5/17 5:34 PM
 * Editor: ldy
 * Modify Date: 3/5/17 5:34 PM
 * Remark:
 */
public class JsonUtils {

    private JsonUtils() {
        throw new Error("Do not need instantiate!");
    }

    public static <T> T parseObjectFromData(JSONObject json, Class<T> clazz) {
        return parseObject(json, "result", clazz);
    }

    public static <T> T parseObject(JSONObject json, String key, Class<T> clazz) {
        return parseObject(json.getJSONObject(key), clazz);
    }

    public static <T> T parseObject(JSONObject json, Class<T> clazz) {
        return JSON.toJavaObject(json, clazz);
    }

    public static <T> T parseObject(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    public static <T> List<T> parseListFromData(JSONObject json, Class<T> clazz) {
        return parseList(json, "result", clazz);
    }

    public static <T> List<T> parseList(JSONObject json, String key, Class<T> clazz) {
        return parseList(json.getJSONArray(key).toJSONString(), clazz);
    }

    public static <T> List<T> parseList(JSONArray jsonArray, Class<T> clazz) {
        return parseList(jsonArray.toJSONString(), clazz);
    }

    public static <T> List<T> parseList(String string, Class<T> clazz) {
        return JSON.parseArray(string, clazz);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
