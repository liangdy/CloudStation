package com.cloud.ui;

import android.support.annotation.NonNull;

/**
 * Project: CloudStation
 * FileName: BasePresenter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 2/22/17 4:43 PM
 * Editor: ldy
 * Modify Date: 2/22/17 4:43 PM
 * Remark:
 */
public interface BasePresenter<T extends BaseView> {

    void attachView(@NonNull T view);

    void detachView();
}
