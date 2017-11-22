package com.magical.library.load.callback;

import android.content.Context;
import android.view.View;

/**
 * Project: CloudStation
 * FileName: SuccessCallback.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 10/3/17 7:58 PM
 * Editor: ldy
 * Modify Date: 10/3/17 7:58 PM
 * Remark:
 */
public class SuccessCallback extends Callback {
    public SuccessCallback(View view, Context context, OnReloadListener onReloadListener) {
        super(view, context, onReloadListener);
    }

    @Override
    protected int onCreateView() {
        return 0;
    }
}
