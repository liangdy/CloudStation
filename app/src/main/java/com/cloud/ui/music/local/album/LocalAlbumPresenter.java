package com.cloud.ui.music.local.album;

import android.support.annotation.NonNull;

import com.cloud.model.music.AlbumInfo;
import com.cloud.ui.BaseActivity;
import com.cloud.ui.adapter.LocalAlbumAdapter;
import com.cloud.ui.music.util.MusicManager;

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
 * FileName: LocalAlbumPresenter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/5/17 7:42 PM
 * Editor: ldy
 * Modify Date: 9/5/17 7:42 PM
 * Remark:
 */
public class LocalAlbumPresenter implements LocalAlbumContract.Presenter {

    private BaseActivity mActivity;

    private LocalAlbumContract.View mLocalAlbumView;

    private Disposable mDisposable;

    @Inject
    public LocalAlbumPresenter(BaseActivity activity) {
        this.mActivity = activity;
    }

    @Override
    public void attachView(@NonNull LocalAlbumContract.View view) {
        mLocalAlbumView = view;
        mLocalAlbumView.initialized();
        mLocalAlbumView.loadAlbumInfo();
    }

    @Override
    public void getAlbumInfo() {
        Observable.fromArray(MusicManager.queryAlbums(mActivity))
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Disposable disposable) throws Exception {
                        mDisposable = disposable;
                    }
                }).filter(new Predicate<List<AlbumInfo>>() {
            @Override
            public boolean test(@io.reactivex.annotations.NonNull List<AlbumInfo> albumInfos) throws Exception {
                return (albumInfos != null && !albumInfos.isEmpty());
            }
        }).subscribe(new Consumer<List<AlbumInfo>>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull List<AlbumInfo> albumInfos) throws Exception {
                mLocalAlbumView.updateAlbumInfo(albumInfos);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        });
    }

    @Override
    public void updateAlbumInfo(LocalAlbumAdapter adapter, List<AlbumInfo> albumInfos) {
        adapter.updateData(albumInfos);
    }

    @Override
    public void detachView() {
        mLocalAlbumView = null;
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
