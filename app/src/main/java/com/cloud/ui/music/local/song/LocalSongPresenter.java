package com.cloud.ui.music.local.song;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.cloud.model.music.MusicInfo;
import com.cloud.ui.BaseActivity;
import com.cloud.ui.adapter.LocalSongAdapter;
import com.cloud.ui.music.util.IConstants;
import com.cloud.ui.music.util.MusicManager;
import com.cloud.ui.music.util.PreferencesUtility;
import com.cloud.ui.music.util.SortOrder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Project: CloudStation
 * FileName: LocalSongPresenter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/4/17 10:26 AM
 * Editor: ldy
 * Modify Date: 9/4/17 10:26 AM
 * Remark:
 */
public class LocalSongPresenter implements LocalSongContract.Presenter {

    private BaseActivity mActivity;

    private LocalSongContract.View mLocalSongView;

    private Disposable mDisposable;

    private List<MusicInfo> mMusicInfos = new ArrayList<>();

    @Inject
    public LocalSongPresenter(BaseActivity activity) {
        this.mActivity = activity;
    }

    @Override
    public void attachView(@NonNull LocalSongContract.View view) {
        mLocalSongView = view;
        mLocalSongView.initialized();
        mLocalSongView.initHeaderView();
        mLocalSongView.loadLocalSong();
    }

    @Override
    public void getLocalSong() {
        Observable.fromArray(MusicManager.queryMusic(mActivity, IConstants.START_FROM_LOCAL))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Disposable disposable) throws Exception {
                        mDisposable = disposable;
                    }
                }).filter(new Predicate<List<MusicInfo>>() {
            @Override
            public boolean test(@io.reactivex.annotations.NonNull List<MusicInfo> musicInfos) throws Exception {
                return (musicInfos != null && !musicInfos.isEmpty());
            }
        }).subscribe(new Consumer<List<MusicInfo>>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull List<MusicInfo> musicInfos) throws Exception {
                mMusicInfos.addAll(musicInfos);
                mLocalSongView.updateLocalSong(musicInfos);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                mLocalSongView.setHeaderCount(mMusicInfos.size());
            }
        });
    }

    @Override
    public void updateLocalSong(LocalSongAdapter adapter, List<MusicInfo> musicInfos) {
        boolean isAZSort = PreferencesUtility.getInstance(mActivity).getSongSortOrder().equals(SortOrder.SongSortOrder.SONG_A_Z);
        if (isAZSort) {
            Collections.sort(musicInfos, new Comparator<MusicInfo>() {
                @Override
                public int compare(MusicInfo m1, MusicInfo m2) {
                    String py1 = m1.sort;
                    String py2 = m2.sort;
                    if (TextUtils.isEmpty(py1) && TextUtils.isEmpty(py2)) {
                        return 0;
                    }
                    if (TextUtils.isEmpty(py1)) {
                        return -1;
                    }
                    if (TextUtils.isEmpty(py2)) {
                        return 1;
                    }
                    return py1.compareTo(py2);
                }
            });
        }
        adapter.updateData(musicInfos);
    }

    @Override
    public void detachView() {
        mLocalSongView = null;
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
