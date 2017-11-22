package com.cloud.ui.music.play;

import android.support.annotation.NonNull;

import com.cloud.ui.BaseActivity;

import javax.inject.Inject;

/**
 * Project: CloudStation
 * FileName: PlayPresenter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/3/17 3:34 PM
 * Editor: ldy
 * Modify Date: 9/3/17 3:34 PM
 * Remark:
 */
public class PlayPresenter implements PlayContract.Presenter {

    private PlayContract.View mPlayView;

    private BaseActivity mActivity;

    @Inject
    public PlayPresenter(BaseActivity activity) {
        this.mActivity = activity;
    }

    @Override
    public void attachView(@NonNull PlayContract.View view) {
        mPlayView = view;
        mPlayView.initialized();
    }

    @Override
    public void detachView() {
        mPlayView = null;
    }

    @Override
    public void onBack() {
        mActivity.finish();
    }
}
