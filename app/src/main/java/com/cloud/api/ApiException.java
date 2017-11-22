package com.cloud.api;

/**
 * Project: CloudStation
 * FileName: ApiException.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 4/5/17 11:07 AM
 * Editor: ldy
 * Modify Date: 4/5/17 11:07 AM
 * Remark:
 */
public class ApiException extends Exception {
    public int code;
    public String message;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }
}
