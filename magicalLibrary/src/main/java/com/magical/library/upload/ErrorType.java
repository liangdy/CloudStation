package com.magical.library.upload;

/**
 * Project: TShow
 * FileName: ErrorType.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
public class ErrorType {
    public static final int ERROR_TYPE_UNKNOWN = 0;

    //网络连接异常等
    public static final int ERROR_TYPE_IO_ERROR = 1;

    //业务逻辑错误
    public static final int ERROR_TYPE_BUSINESS_LOGIC_ERROR = 2;

    //返回数据解析错误
    public static final int ERROR_TYPE_PARSE_DATA_ERROR = 3;

    //取消
    public static final int ERROR_TYPE_CANCELED = 4;
}