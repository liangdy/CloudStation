package com.cloud.ui.music.recd;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.cloud.api.CloudApi;
import com.cloud.model.music.Album;
import com.cloud.model.music.Banner;
import com.cloud.model.music.Music;
import com.cloud.model.music.RecdList;
import com.cloud.model.music.SongSheet;
import com.cloud.ui.BaseActivity;
import com.cloud.ui.adapter.RecommendAdapter;
import com.cloud.ui.music.detail.album.AlbumDetailActivity;
import com.cloud.ui.music.detail.songsheet.SongSheetDetailActivity;
import com.cloud.view.BannerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Project: CloudStation
 * FileName: RecdPresenter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/10/17 2:41 PM
 * Editor: ldy
 * Modify Date: 3/10/17 2:41 PM
 * Remark:
 */
public class RecommendPresenter implements RecommendContract.Presenter {

    private BaseActivity mActivity;
    private RecommendContract.View mRecdSongView;
    private Disposable mDisposable;

    private List<RecdList> recdLists = new ArrayList<>();

    private Banner cBanner;
    private SongSheet cSongSheet;
    private Album cAlbum;
    private Music cMusic;

    @Inject
    public RecommendPresenter(BaseActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void attachView(@NonNull RecommendContract.View view) {
        recdLists.clear();
        mRecdSongView = view;
        mRecdSongView.initialized();
        mRecdSongView.initHeader();
        mRecdSongView.showLoadProgress();
        mRecdSongView.loadData();
    }

    @Override
    public void detachView() {
        mRecdSongView = null;
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    public void getBanner() {
        CloudApi.getBanner(8).observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mDisposable = disposable;
                    }
                }).subscribe(new Consumer<Banner>() {
            @Override
            public void accept(Banner banner) throws Exception {
                cBanner = banner;
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("throwable:" + throwable.toString());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                mRecdSongView.loadBanner(cBanner);
            }
        });
    }

    @Override
    public void getRecdSongSheet() {
        CloudApi.getHotSongSheet(6).observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mDisposable = disposable;
                    }
                }).subscribe(new Consumer<SongSheet>() {
            @Override
            public void accept(SongSheet songSheet) throws Exception {
                cSongSheet = songSheet;
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                RecdList recdList = new RecdList();
                recdList.object = cSongSheet;
                recdList.type = RecdList.TYPE_HOT_SONG_SHEET;
                recdLists.add(recdList);
                mRecdSongView.updateRecdData(recdLists);
            }
        });
    }

    @Override
    public void getRecdAlbum() {
        CloudApi.getRecdAlbum(10, 6).observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mDisposable = disposable;
                    }
                }).subscribe(new Consumer<Album>() {
            @Override
            public void accept(Album album) throws Exception {
                cAlbum = album;
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                RecdList recdList = new RecdList();
                recdList.object = cAlbum;
                recdList.type = RecdList.TYPE_RECD_ALBUM;
                recdLists.add(recdList);
                mRecdSongView.updateRecdData(recdLists);
            }
        });
    }

    @Override
    public void getRecdMusic() {
        CloudApi.getRecdMusic(10).observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mDisposable = disposable;
                    }
                }).subscribe(new Consumer<Music>() {
            @Override
            public void accept(Music music) throws Exception {
                cMusic = music;
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                RecdList recdList = new RecdList();
                recdList.object = cMusic;
                recdList.type = RecdList.TYPE_RECD_MUSIC;
                recdLists.add(recdList);
                mRecdSongView.updateRecdData(recdLists);
            }
        });
    }

    @Override
    public void loadBanner(Banner banner, BannerView bannerView) {
        if (banner == null || banner.pic == null || banner.pic.isEmpty()) {
            bannerView.setVisibility(View.GONE);
            return;
        }
        List<String> urls = new ArrayList<>();
        for (Banner.PicBean picBean : banner.pic) {
            urls.add(picBean.randpic);
        }
        bannerView.setImgUrls(urls);
    }

    @Override
    public void updateRecdData(RecommendAdapter mAdapter, List<RecdList> recdLists) {
        mAdapter.updateData(recdLists);
    }

    @Override
    public void goSongSheetDetail(String listId) {
        Intent intent = new Intent(mActivity, SongSheetDetailActivity.class);
        intent.putExtra("listid", listId);
        mActivity.startActivity(intent);
    }

    @Override
    public void goAlbumDetail(String albumId) {
        Intent intent = new Intent(mActivity, AlbumDetailActivity.class);
        intent.putExtra("album_id", albumId);
        mActivity.startActivity(intent);
    }

    @Override
    public void play(Music.SongListBean songListBean) {

    }

    @Override
    public void actionMore() {

    }
}
