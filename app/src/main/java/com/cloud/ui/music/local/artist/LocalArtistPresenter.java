package com.cloud.ui.music.local.artist;

import android.support.annotation.NonNull;

import com.cloud.api.CloudApi;
import com.cloud.model.music.ArtistInfo;
import com.cloud.model.music.LastfmArtist;
import com.cloud.ui.BaseActivity;
import com.cloud.ui.adapter.LocalArtistAdapter;
import com.cloud.ui.music.util.MusicManager;
import com.magical.library.view.XImageView;

import java.util.ArrayList;
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
 * FileName: LocalSingerPresenter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/4/17 3:22 PM
 * Editor: ldy
 * Modify Date: 9/4/17 3:22 PM
 * Remark:
 */
public class LocalArtistPresenter implements LocalArtistContract.Presenter {

    private BaseActivity mActivity;

    private LocalArtistContract.View mLocalArtistView;

    private Disposable mDisposable;

    private List<ArtistInfo> mArtistInfos = new ArrayList<>();

    @Inject
    public LocalArtistPresenter(BaseActivity activity) {
        this.mActivity = activity;
    }

    @Override
    public void attachView(@NonNull LocalArtistContract.View view) {
        mLocalArtistView = view;
        mLocalArtistView.initialized();
        mLocalArtistView.loadArtistInfo();
    }

    @Override
    public void getArtistInfo() {
        Observable.fromArray(MusicManager.queryArtist(mActivity))
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Disposable disposable) throws Exception {
                        mDisposable = disposable;
                    }
                }).filter(new Predicate<List<ArtistInfo>>() {
            @Override
            public boolean test(@io.reactivex.annotations.NonNull List<ArtistInfo> artistInfos) throws Exception {
                return (artistInfos != null && !artistInfos.isEmpty());
            }
        }).subscribe(new Consumer<List<ArtistInfo>>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull List<ArtistInfo> artistInfos) throws Exception {
                mArtistInfos.addAll(artistInfos);
                mLocalArtistView.updateArtistInfo(artistInfos);
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
    public void updateArtistInfo(LocalArtistAdapter adapter, List<ArtistInfo> artistInfos) {
        adapter.updateData(artistInfos);
    }

    @Override
    public void loadImage(final XImageView iv, String artist) {
        CloudApi.getArtistInfo(artist)
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Disposable disposable) throws Exception {
                        mDisposable = disposable;
                    }
                })
                .filter(new Predicate<LastfmArtist>() {
                    @Override
                    public boolean test(@io.reactivex.annotations.NonNull LastfmArtist artistInfo) throws Exception {
                        return (artistInfo != null && artistInfo.artist != null
                                && artistInfo.artist.image != null && !artistInfo.artist.image.isEmpty());
                    }
                }).subscribe(new Consumer<LastfmArtist>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull LastfmArtist artistInfo) throws Exception {
                mLocalArtistView.updateImage(iv, artistInfo.artist.image.get(2).url);
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
    public void detachView() {
        mLocalArtistView = null;
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
