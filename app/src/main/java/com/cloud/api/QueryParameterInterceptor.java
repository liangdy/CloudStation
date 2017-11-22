package com.cloud.api;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Project: CloudStation
 * FileName: QueryParameterInterceptor.java
 * Description:公共参数拦截器
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/2/17 3:08 PM
 * Editor: ldy
 * Modify Date: 8/2/17 3:08 PM
 * Remark:
 */
public class QueryParameterInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originalRequest = chain.request();
        Request request;
        String method = originalRequest.method();
        Headers headers = originalRequest.headers();
        HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                // Provide your custom parameter here
                .addQueryParameter("from", "android")
                .addQueryParameter("version", "5.9.9.3")
                .addQueryParameter("format", "json")
                .build();
        request = originalRequest.newBuilder().url(modifiedUrl).build();
        return chain.proceed(request);
    }
}
