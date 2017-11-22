package com.cloud.ui.music.detail.songsheet;

import android.support.annotation.NonNull;

import com.cloud.R;
import com.cloud.api.CloudApi;
import com.cloud.model.music.ContentBean;
import com.cloud.model.music.SongSheetDetail;
import com.cloud.ui.BaseActivity;
import com.cloud.ui.adapter.SongSheetDetailAdapter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Project: CloudStation
 * FileName: SongSheetDetailPresenter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/22/17 5:04 PM
 * Editor: ldy
 * Modify Date: 8/22/17 5:04 PM
 * Remark:
 */
public class SongSheetDetailPresenter implements SongSheetDetailContract.Presenter {

    private SongSheetDetailContract.View mSongSheetDetailView;

    private BaseActivity mActivity;

    private Disposable mDisposable;

    private SongSheetDetail mSongSheetDetail;

    @Inject
    public SongSheetDetailPresenter(BaseActivity activity) {
        mActivity = activity;
    }

    @Override
    public void attachView(@NonNull SongSheetDetailContract.View view) {
        mSongSheetDetailView = view;
        mSongSheetDetailView.initialized();
        mSongSheetDetailView.initHeaderView();
        mSongSheetDetailView.loadSongSheetDetail();
    }

    @Override
    public void getSongSheetDetail(String listid) {
        CloudApi.getSongSheetDetail(listid)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Disposable disposable) throws Exception {
                        mDisposable = disposable;
                    }
                }).filter(new Predicate<SongSheetDetail>() {
            @Override
            public boolean test(@io.reactivex.annotations.NonNull SongSheetDetail songSheetDetail) throws Exception {
                return (songSheetDetail != null && songSheetDetail.error_code == 22000);
            }
        }).subscribe(new Consumer<SongSheetDetail>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull SongSheetDetail songSheetDetail) throws Exception {
                mSongSheetDetail = songSheetDetail;
                mSongSheetDetailView.setSongSheetInfo(mSongSheetDetail);
                if (mSongSheetDetail.content != null && !mSongSheetDetail.content.isEmpty()) {
                    mSongSheetDetailView.setHeaderCount(mSongSheetDetail.content.size());
                    mSongSheetDetailView.loadSongList(mSongSheetDetail.content);
                } else {
                    mSongSheetDetailView.setHeaderCount(0);
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
    public void setToolBarStatus(int offset, int maxScroll) {
        if (mSongSheetDetail == null) {
            return;
        }
        mSongSheetDetailView.setToolBarSubTitle(mSongSheetDetail.desc);
        float percent = (float) Math.abs(offset) / (float) maxScroll;
        String title = mSongSheetDetail.title;
        if (percent < 0.4) {
            mSongSheetDetailView.setToolBarBgAlpha((int) (percent * 2.5 * 255));
            mSongSheetDetailView.setInfoAlpha((float) (1 - percent / 0.7));
            mSongSheetDetailView.setToolBarTitle(mActivity.getString(R.string.song_sheet));
            mSongSheetDetailView.setOperationAlpha(1);
            //mSongSheetDetailView.setBottomOperationClickable(true);
        } else if (percent < 0.7 && percent > 0.4) {
            mSongSheetDetailView.setToolBarBgAlpha(255);
            mSongSheetDetailView.setToolBarTitle(title);
            mSongSheetDetailView.setInfoAlpha((float) (1 - percent / 0.7));
            mSongSheetDetailView.setOperationAlpha(1);
            //mSongSheetDetailView.setBottomOperationClickable(true);
        } else {
            mSongSheetDetailView.setToolBarBgAlpha(255);
            mSongSheetDetailView.setInfoAlpha((float) (0));
            mSongSheetDetailView.setToolBarTitle(title);
            mSongSheetDetailView.setOperationAlpha((float) ((0.9 - percent) / 0.2));

            if (percent > 0.9) {
                //mSongSheetDetailView.setBottomOperationClickable(false);
            } else {
                //mSongSheetDetailView.setBottomOperationClickable(true);
            }
        }
    }

    @Override
    public void updateSongSheet(SongSheetDetailAdapter adapter, List<ContentBean> contentBeans) {
        adapter.updateData(contentBeans);
    }

    @Override
    public void doSearch() {

    }

    @Override
    public void onBack() {
        mActivity.finish();
    }

    @Override
    public void detachView() {
        mSongSheetDetailView = null;
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
