package com.cloud.ui.music.detail.album;

import android.support.annotation.NonNull;

import com.cloud.R;
import com.cloud.api.CloudApi;
import com.cloud.model.music.AlbumDetail;
import com.cloud.ui.BaseActivity;
import com.cloud.ui.adapter.AlbumDetailAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Project: CloudStation
 * FileName: AlbumDetailPresenter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/29/17 4:41 PM
 * Editor: ldy
 * Modify Date: 8/29/17 4:41 PM
 * Remark:
 */
public class AlbumDetailPresenter implements AlbumDetailContract.Presenter {

    private BaseActivity mActivity;

    private AlbumDetailContract.View albumDetailView;

    private AlbumDetail mAlbumDetail;

    private List<AlbumDetail.SongListBean> songListBeans = new ArrayList<>();

    private Disposable mDisposable;

    @Inject
    public AlbumDetailPresenter(BaseActivity activity) {
        mActivity = activity;
    }

    @Override
    public void attachView(@NonNull AlbumDetailContract.View view) {
        albumDetailView = view;
        albumDetailView.initialized();
        albumDetailView.initHeaderView();
        albumDetailView.loadAlbumDetail();
    }

    @Override
    public void getAlbumDetail(String albumId) {
        CloudApi.getAlbumDetail(albumId).observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Disposable disposable) throws Exception {
                        mDisposable = disposable;
                    }
                }).filter(new Predicate<AlbumDetail>() {
            @Override
            public boolean test(@io.reactivex.annotations.NonNull AlbumDetail albumDetail) throws Exception {
                return (albumDetail != null && albumDetail.albumInfo != null);
            }
        }).subscribe(new Consumer<AlbumDetail>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull AlbumDetail albumDetail) throws Exception {
                mAlbumDetail = albumDetail;
                albumDetailView.setAlbumInfo(mAlbumDetail.albumInfo);
                if (mAlbumDetail.songlist != null && !mAlbumDetail.songlist.isEmpty()) {
                    albumDetailView.setHeaderCount(mAlbumDetail.songlist.size());
                    albumDetailView.loadSongList(mAlbumDetail.songlist);
                } else {
                    albumDetailView.setHeaderCount(0);
                }
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
    public void onBack() {
        mActivity.finish();
    }

    @Override
    public void doSearch() {

    }

    @Override
    public void setToolBarStatus(int offset, int maxScroll) {
        if (mAlbumDetail == null || mAlbumDetail.albumInfo == null) {
            return;
        }
        albumDetailView.setToolBarSubTitle(mAlbumDetail.albumInfo.author);
        float percent = (float) Math.abs(offset) / (float) maxScroll;
        String title = mAlbumDetail.albumInfo.title;
        if (percent < 0.4) {
            albumDetailView.setToolBarBgAlpha((int) (percent * 2.5 * 255));
            albumDetailView.setInfoAlpha((float) (1 - percent / 0.7));
            albumDetailView.setToolBarTitle(mActivity.getString(R.string.detail_album));
            albumDetailView.setOperationAlpha(1);
            //albumDetailView.setBottomOperationClickable(true);
        } else if (percent < 0.7 && percent > 0.4) {
            albumDetailView.setToolBarBgAlpha(255);
            albumDetailView.setToolBarTitle(title);
            albumDetailView.setInfoAlpha((float) (1 - percent / 0.7));
            albumDetailView.setOperationAlpha(1);
            //albumDetailView.setBottomOperationClickable(true);
        } else {
            albumDetailView.setToolBarBgAlpha(255);
            albumDetailView.setInfoAlpha((float) (0));
            albumDetailView.setToolBarTitle(title);
            albumDetailView.setOperationAlpha((float) ((0.9 - percent) / 0.2));

            if (percent > 0.9) {
                //mSongSheetDetailView.setBottomOperationClickable(false);
            } else {
                //mSongSheetDetailView.setBottomOperationClickable(true);
            }
        }
    }

    @Override
    public void updateAlbum(AlbumDetailAdapter adapter, List<AlbumDetail.SongListBean> songListBeans) {
        adapter.updateData(songListBeans);
    }

    @Override
    public void detachView() {
        albumDetailView = null;
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}