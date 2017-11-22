package com.cloud.api;

import android.support.v4.util.ArrayMap;

import com.cloud.BuildConfig;
import com.cloud.CloudApplication;
import com.cloud.utils.StorageUtils;
import com.magical.library.json.FastJsonConverterFactory;
import com.magical.library.utils.AppUtils;
import com.magical.library.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Project: CloudStation
 * FileName: CloudClient.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 2/22/17 4:46 PM
 * Editor: ldy
 * Modify Date: 2/22/17 4:46 PM
 * Remark:
 */
public class CloudClient {

    public static final int LIMIT = 20;
    private static File cacheFile = new File(StorageUtils.getCacheDir(), "net_cache");
    private static Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);

    public static int status = 0;//0:音乐 1:lastfm_api 2:视频 3:图片 4:搞笑 

    private static final String MUSIC_BASE_URL = "http://tingapi.ting.baidu.com/v1/restserver/";
    private static final String FM_BASE_URL = "http://ws.audioscrobbler.com/";
    private static final String VIDEO_BASE_URL = "";
    private static final String PICTURE_BASE_URL = "";
    private static final String JOKE_BASE_URL = "";

    public static final String[] BASE_URL = {MUSIC_BASE_URL, FM_BASE_URL, VIDEO_BASE_URL, PICTURE_BASE_URL, JOKE_BASE_URL};

    private CloudClient() {

    }

    /*static {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL[status]).client(getOkHttpClient(status))
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }*/


    public static CloudService getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL[status]).client(getOkHttpClient(status))
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(CloudService.class);
    }

    // 设置超时时间
    private final static long DEFAULT_TIMEOUT = 30;

    public static OkHttpClient getOkHttpClient(int status) {
        // 定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        //添加公共参数
        httpClientBuilder.addInterceptor(new QueryParameterInterceptor());
        //Log信息拦截器
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(loggingInterceptor);
        }
        if (status == 0) { //音乐
            httpClientBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request()
                            .newBuilder()
                            // .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36")
                            .addHeader("User-Agent", "android_5.9.9.3;baiduyinyue")
                            //.addHeader("Cache-Control", "max-age=0")
                            .build();
                    return chain.proceed(request);
                }
            });
        }

        //缓存机制
        httpClientBuilder.cache(cache).addInterceptor(new CacheInterceptor());

        // 设置超时时间
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        return httpClientBuilder.build();
    }

    private static Map<String, String> getHeader() {
        String did = AppUtils.getAndroidUUID(CloudApplication.getApplication());
        String brand = URLEncoder.encode(android.os.Build.BRAND);
        String versionCode = AppUtils.getPackageInfo(CloudApplication
                .getApplication()).versionCode + "";
        String versionName = AppUtils
                .getPackageInfo(CloudApplication.getApplication()).versionName;
        Map<String, String> headers = getParams("did", did, "brand", brand, "versionCode", versionCode, "versionName", versionName);
        return headers;
    }

    public static Map<String, String> getParams(Object... keyAndParams) {
        if (keyAndParams.length % 2 != 0) {
            throw new IllegalArgumentException("Parameters of getParams() must appear in pairs");
        }
        Map<String, String> params = new ArrayMap<>();
        for (int i = 0; i < keyAndParams.length; i += 2) {
            if (StringUtils.isNotEmpty(keyAndParams[i]) &&
                    StringUtils.isNotEmpty(keyAndParams[i + 1])) {
                params.put(keyAndParams[i].toString(), keyAndParams[i + 1]
                        .toString());
            }
        }
        return params;
    }
}
