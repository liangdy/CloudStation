package com.cloud.ui.music.detail.ranking;

import android.support.annotation.NonNull;

import com.cloud.R;
import com.cloud.api.CloudApi;
import com.cloud.model.music.ContentBean;
import com.cloud.model.music.RankDetail;
import com.cloud.ui.BaseActivity;
import com.cloud.ui.adapter.RankingDetailAdapter;

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
 * FileName: RankingDetailPresenter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/1/17 6:42 PM
 * Editor: ldy
 * Modify Date: 9/1/17 6:42 PM
 * Remark:
 */
public class RankingDetailPresenter implements RankingDetailContract.Presenter {

    private BaseActivity mActivity;
    private Disposable mDisposable;

    private RankingDetailContract.View mRankingDetailView;

    private RankDetail mRankDetail;
    private List<ContentBean> songs = new ArrayList<>();

    @Inject
    public RankingDetailPresenter(BaseActivity activity) {
        this.mActivity = activity;
    }

    @Override
    public void attachView(@NonNull RankingDetailContract.View view) {
        mRankingDetailView = view;
        mRankingDetailView.initialized();
        mRankingDetailView.initHeaderView();
        mRankingDetailView.loadRankingDetail();
    }

    @Override
    public void getRankingDetail(String type) {
        int offset = 0;
        int size = 300;
        CloudApi.getRankDetail(type, offset, size).observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Disposable disposable) throws Exception {
                        mDisposable = disposable;
                    }
                }).filter(new Predicate<RankDetail>() {
            @Override
            public boolean test(@io.reactivex.annotations.NonNull RankDetail rankDetail) throws Exception {
                return (rankDetail != null && rankDetail.billboard != null);
            }
        }).subscribe(new Consumer<RankDetail>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull RankDetail rankDetail) throws Exception {
                mRankDetail = rankDetail;
                mRankingDetailView.setRankingInfo(rankDetail.billboard);
                if (rankDetail.song_list != null && !rankDetail.song_list.isEmpty()) {
                    mRankingDetailView.setHeaderCount(rankDetail.song_list.size());
                    mRankingDetailView.loadSongList(rankDetail.song_list);
                } else {
                    mRankingDetailView.setHeaderCount(0);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                System.out.println("throwable:" + throwable.toString());
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
        if (mRankDetail == null || mRankDetail.billboard == null) {
            return;
        }
        mRankingDetailView.setToolBarSubTitle(mActivity.getString(R.string.detail_ranking_update_date) + ": " + mRankDetail.billboard.update_date);
        float percent = (float) Math.abs(offset) / (float) maxScroll;
        String title = mRankDetail.billboard.name;
        if (percent < 0.4) {
            mRankingDetailView.setToolBarBgAlpha((int) (percent * 2.5 * 255));
            mRankingDetailView.setInfoAlpha((float) (1 - percent / 0.7));
            mRankingDetailView.setToolBarTitle(mActivity.getString(R.string.detail_ranking));
            mRankingDetailView.setOperationAlpha(1);
            //albumDetailView.setBottomOperationClickable(true);
        } else if (percent < 0.7 && percent > 0.4) {
            mRankingDetailView.setToolBarBgAlpha(255);
            mRankingDetailView.setToolBarTitle(title);
            mRankingDetailView.setInfoAlpha((float) (1 - percent / 0.7));
            mRankingDetailView.setOperationAlpha(1);
            //albumDetailView.setBottomOperationClickable(true);
        } else {
            mRankingDetailView.setToolBarBgAlpha(255);
            mRankingDetailView.setInfoAlpha((float) (0));
            mRankingDetailView.setToolBarTitle(title);
            mRankingDetailView.setOperationAlpha((float) ((0.9 - percent) / 0.2));

            if (percent > 0.9) {
                //mSongSheetDetailView.setBottomOperationClickable(false);
            } else {
                //mSongSheetDetailView.setBottomOperationClickable(true);
            }
        }
    }

    @Override
    public void updateRanking(RankingDetailAdapter adapter, List<ContentBean> contentBeans) {
        adapter.updateData(contentBeans);
    }

    @Override
    public void detachView() {
        mRankingDetailView = null;
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
