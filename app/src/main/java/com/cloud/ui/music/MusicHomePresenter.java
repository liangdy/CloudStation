package com.cloud.ui.music;

import android.support.annotation.NonNull;

import com.cloud.service.AudioPlayServiceManager;
import com.cloud.ui.BaseActivity;

import javax.inject.Inject;

/**
 * Project: CloudStation
 * FileName: MusicHomePresenter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/3/17 10:52 AM
 * Editor: ldy
 * Modify Date: 3/3/17 10:52 AM
 * Remark:
 */
public class MusicHomePresenter implements MusicHomeContract.Presenter {

    private BaseActivity mActivity;
    private MusicHomeContract.View mMusicHomeView;

    @Inject
    public MusicHomePresenter(BaseActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void attachView(@NonNull MusicHomeContract.View view) {
        AudioPlayServiceManager.getInstance().initConn();
        AudioPlayServiceManager.getInstance().connectService();
        mMusicHomeView = view;
        mMusicHomeView.initialized();
    }

    @Override
    public void detachView() {
        mMusicHomeView = null;
    }

}
