package com.cloud.api;

import android.net.ParseException;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONPathException;

import java.net.ConnectException;

import retrofit2.HttpException;

/**
 * Project: CloudStation
 * FileName: ExceptionEngine.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 4/5/17 11:06 AM
 * Editor: ldy
 * Modify Date: 4/5/17 11:06 AM
 * Remark:
 */
public class ExceptionEngine {

    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof HttpException) {             //HTTP错误
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, Error.HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.message = "网络错误";  //均视为网络错误
                    break;
            }
            return ex;
        } else if (e instanceof ServerException) {    //服务器返回的错误
            ServerException resultException = (ServerException) e;
            ex = new ApiException(resultException, resultException.code);
            ex.message = resultException.message;
            return ex;
        } else if (e instanceof JSONPathException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ApiException(e, Error.PARSE_ERROR);
            ex.message = "解析错误";            //均视为解析错误
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ApiException(e, Error.NETWORD_ERROR);
            ex.message = "连接失败";  //均视为网络错误
            return ex;
        } else {
            ex = new ApiException(e, Error.UNKNOWN);
            ex.message = "未知错误";          //未知错误
            return ex;
        }
    }
}
