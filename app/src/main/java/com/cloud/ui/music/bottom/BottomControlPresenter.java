package com.cloud.ui.music.bottom;

import android.support.annotation.NonNull;

import com.cloud.ui.BaseActivity;

import javax.inject.Inject;

/**
 * Project: CloudStation
 * FileName: BottomControlPresenter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 12/2/17 4:09 PM
 * Editor: ldy
 * Modify Date: 12/2/17 4:09 PM
 * Remark:
 */
public class BottomControlPresenter implements BottomControlContract.Presenter {

    BaseActivity mActivity;

    BottomControlContract.View mBottomControlView;

    @Inject
    public BottomControlPresenter(BaseActivity activity) {
        mActivity = activity;
    }

    @Override
    public void attachView(@NonNull BottomControlContract.View view) {
        mBottomControlView = view;
        mBottomControlView.initialized();
    }

    @Override
    public void detachView() {
        mBottomControlView = null;
    }
}
