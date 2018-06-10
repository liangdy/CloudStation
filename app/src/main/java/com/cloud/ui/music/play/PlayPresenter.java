package com.cloud.ui.music.play;

import android.support.annotation.NonNull;

import com.cloud.model.music.ContentBean;
import com.cloud.ui.BaseActivity;

import java.util.List;

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
        mActivity = activity;
    }

    @Override
    public void attachView(@NonNull PlayContract.View view) {
        mPlayView = view;
        mPlayView.initialized();
    }

    @Override
    public void onBack() {
        mActivity.finish();
    }

    @Override
    public void play() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void next() {

    }

    @Override
    public void prev() {

    }

    @Override
    public void changeMode() {

    }

    @Override
    public void download() {

    }

    @Override
    public void collect() {

    }

    @Override
    public void more() {

    }

    @Override
    public void showList() {

    }

    @Override
    public ContentBean getCurMusic() {
        return null;
    }

    @Override
    public List<ContentBean> getCurMusicList() {
        return null;
    }

    @Override
    public void detachView() {
        mPlayView = null;
    }
}
