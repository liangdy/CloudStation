package com.magical.library.upload.parser;

/**
 * Project: TShow
 * FileName: ParserResult.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
public abstract class ParserResult<T> {

    public T data;

    public ParserResult(T data) {
        this.data = data;
    }

    public abstract boolean isSuccessful();

    public abstract String getMsg();

}