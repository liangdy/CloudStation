package com.cloud.ui.music.ranking;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.cloud.api.CloudApi;
import com.cloud.model.music.RankBean;
import com.cloud.ui.BaseActivity;
import com.cloud.ui.adapter.RankingAdapter;
import com.cloud.ui.music.detail.ranking.RankingDetailActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Project: CloudStation
 * FileName: RankingPresenter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/14/17 10:34 AM
 * Editor: ldy
 * Modify Date: 8/14/17 10:34 AM
 * Remark:
 */
public class RankingPresenter implements RankingContract.Presenter {

    private BaseActivity mActivity;

    private RankingContract.View mRankingView;

    private Disposable mDisposable;

    private List<RankBean.ContentBean> mRankbeans = new ArrayList<>();

    @Inject
    public RankingPresenter(BaseActivity activity) {
        mActivity = activity;
    }

    @Override
    public void attachView(@NonNull RankingContract.View view) {
        mRankingView = view;
        mRankingView.initRecyclerView();
        mRankingView.loadRank();
    }

    @Override
    public void detachView() {
        mRankingView = null;
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    public void getRank() {
        CloudApi.getAllRank(1).observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mDisposable = disposable;
                    }
                }).subscribe(new Consumer<RankBean>() {
            @Override
            public void accept(RankBean rankBean) throws Exception {
                mRankbeans.clear();
                mRankbeans.addAll(rankBean.content);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                mRankingView.updateRank(mRankbeans);
            }
        });
    }

    @Override
    public void updateRank(RankingAdapter adapter, List<RankBean.ContentBean> rankBeans) {
        adapter.updateData(rankBeans);
    }

    @Override
    public void goRankingDetail(int position) {
        String type = String.valueOf(mRankbeans.get(position).type);
        Intent intent = new Intent(mActivity, RankingDetailActivity.class);
        intent.putExtra("type", type);
        mActivity.startActivity(intent);
    }
}
