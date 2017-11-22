package com.cloud.ui.music.local;

import android.support.annotation.NonNull;

import com.cloud.ui.BaseActivity;

import javax.inject.Inject;

/**
 * Project: CloudStation
 * FileName: LocalMusicPresenter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/3/17 4:35 PM
 * Editor: ldy
 * Modify Date: 9/3/17 4:35 PM
 * Remark:
 */
public class LocalMusicPresenter implements LocalMusicContract.Presenter {

    private BaseActivity mActivity;

    private LocalMusicContract.View mLocalMusicView;

    @Inject
    public LocalMusicPresenter(BaseActivity activity) {
        this.mActivity = activity;
    }

    @Override
    public void attachView(@NonNull LocalMusicContract.View view) {
        mLocalMusicView = view;
        mLocalMusicView.initialized();
    }

    @Override
    public void onBack() {
        mActivity.finish();
    }

    @Override
    public void detachView() {
        mLocalMusicView = null;
    }
}
