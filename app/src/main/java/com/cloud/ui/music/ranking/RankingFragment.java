package com.cloud.ui.music.ranking;

import android.view.View;

import com.cloud.R;
import com.cloud.model.music.RankBean;
import com.cloud.ui.BaseFragment;
import com.cloud.ui.adapter.RankingAdapter;
import com.magical.library.view.recycler.MagicalRecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Project: CloudStation
 * FileName: RankingFragment.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/3/17 3:34 PM
 * Editor: ldy
 * Modify Date: 3/3/17 3:34 PM
 * Remark:
 */
public class RankingFragment extends BaseFragment implements RankingContract.View {

    private static final String TAG = RankingFragment.class.getSimpleName();

    @BindView(R.id.recd_list)
    MagicalRecyclerView recdList;

    @Inject
    RankingPresenter mPresenter;
    @Inject
    RankingAdapter mAdapter;

    @Override
    protected void initInjector() {
        DaggerRankingComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .rankingModule(new RankingModule())
                .build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_ranking;
    }

    @Override
    public void initData() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void initRecyclerView() {
        if (mAdapter != null) {
            recdList.setAdapter(mAdapter);
        }
        recdList.setOnItemClickListener(new MagicalRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(MagicalRecyclerView magicalRecyclerView, View view, int position) {
                if (mPresenter != null) {
                    mPresenter.goRankingDetail(position);
                }
            }
        });
    }

    @Override
    public void loadRank() {
        if (mPresenter != null) {
            mPresenter.getRank();
        }
    }

    @Override
    public void updateRank(List<RankBean.ContentBean> rankBeans) {
        if (mPresenter != null && mAdapter != null) {
            mPresenter.updateRank(mAdapter, rankBeans);
        }
    }

    @Override
    public String getPageName() {
        return TAG;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
