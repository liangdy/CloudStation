package com.cloud.api;

/**
 * Project: CloudStation
 * FileName: ServerException.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 4/5/17 11:08 AM
 * Editor: ldy
 * Modify Date: 4/5/17 11:08 AM
 * Remark:
 */
public class ServerException extends RuntimeException {
    public int code;
    public String message;
}
