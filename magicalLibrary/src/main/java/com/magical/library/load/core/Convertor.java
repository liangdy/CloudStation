package com.magical.library.load.core;

import com.magical.library.load.callback.Callback;

/**
 * Project: CloudStation
 * FileName: Convertor.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 10/3/17 8:01 PM
 * Editor: ldy
 * Modify Date: 10/3/17 8:01 PM
 * Remark:
 */
public interface Convertor<T> {
    Class<?extends Callback> map(T t);
}
