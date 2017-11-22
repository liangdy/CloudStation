package com.cloud.ui.music.manager;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.cloud.model.music.ArtistInfo;
import com.cloud.model.music.MusicInfo;
import com.cloud.ui.BaseActivity;
import com.cloud.ui.music.local.LocalMusicActivity;
import com.cloud.ui.music.util.IConstants;
import com.cloud.ui.music.util.MusicManager;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Project: CloudStation
 * FileName: ManagerPresenter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/14/17 4:28 PM
 * Editor: ldy
 * Modify Date: 8/14/17 4:28 PM
 * Remark:
 */
public class ManagerPresenter implements ManagerContract.Presenter {

    private BaseActivity mActivity;

    private ManagerContract.View mManagerView;

    private Disposable mDisposable;

    @Inject
    public ManagerPresenter(BaseActivity activity) {
        mActivity = activity;
    }

    @Override
    public void attachView(@NonNull ManagerContract.View view) {
        mManagerView = view;
        loadCount();
    }

    @Override
    public void loadCount() {
        loadLocalMusicCount();
        loadRecentPlayCount();
        loadDownloadCount();
        loadMySingerCount();
    }

    String cMusicCount = null;
    String cDownloadCount = null;
    String cMySingerCount = null;

    private void loadLocalMusicCount() {
        Observable.just(MusicManager.queryMusic(mActivity, IConstants.START_FROM_LOCAL))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mDisposable = disposable;
                    }
                }).map(new Function<List<MusicInfo>, String>() {
            @Override
            public String apply(List<MusicInfo> musicInfos) throws Exception {
                if (musicInfos == null || musicInfos.isEmpty()) {
                    return 0 + "";
                }
                return musicInfos.size() + "";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String count) throws Exception {
                cMusicCount = count;
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                mManagerView.showLocalMusic(cMusicCount);
            }
        });
    }

    private void loadRecentPlayCount() {
        mManagerView.showRecentPlay("0");
    }

    private void loadDownloadCount() {
        mManagerView.showDownload("0");
    }

    private void loadMySingerCount() {
        Observable.just(MusicManager.queryArtist(mActivity))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mDisposable = disposable;
                    }
                }).map(new Function<List<ArtistInfo>, String>() {
            @Override
            public String apply(List<ArtistInfo> artistInfos) throws Exception {
                if (artistInfos == null || artistInfos.isEmpty()) {
                    return 0 + "";
                }
                return artistInfos.size() + "";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String count) throws Exception {
                cMySingerCount = count;
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                mManagerView.showMySinger(cMySingerCount);
            }
        });
    }

    @Override
    public void goLocalMusic(int tab) {
        Intent intent = new Intent(mActivity, LocalMusicActivity.class);
        intent.putExtra("tab", tab);
        mActivity.startActivity(intent);
    }

    @Override
    public void goRecentPlay() {

    }

    @Override
    public void goDownloadManager() {

    }

    @Override
    public void detachView() {
        mManagerView = null;
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}