package com.magical.library.upload;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Project: TShow
 * FileName: CustomHttpClient.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
public class CustomHttpClient {

    private static final int DEFAULT_CONN_TIMEOUT = 30;

    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
            .connectTimeout(DEFAULT_CONN_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_CONN_TIMEOUT, TimeUnit.SECONDS).build();


    private static Call newCall(Request request) {
        Call call = OK_HTTP_CLIENT.newCall(request);
        return call;
    }


    public static void cancelAllRequest() {
        OK_HTTP_CLIENT.dispatcher().cancelAll();
    }

    public static void cancelRequest(Call call) {
        call.cancel();
    }

    public static Response execute(Request request) throws IOException {
        Call call = newCall(request);
        return call.execute();
    }


    public static void executeAsync(Request request, Callback callback) {
        Call call = newCall(request);
        call.enqueue(callback);
    }
}