package com.cloud.ui.music.ranking;

import com.cloud.model.music.RankBean;
import com.cloud.ui.BasePresenter;
import com.cloud.ui.BaseView;
import com.cloud.ui.adapter.RankingAdapter;

import java.util.List;

/**
 * Project: CloudStation
 * FileName: RankingContract.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/14/17 10:32 AM
 * Editor: ldy
 * Modify Date: 8/14/17 10:32 AM
 * Remark:
 */
public interface RankingContract {

    interface View extends BaseView {

        void initRecyclerView();

        void loadRank();

        void updateRank(List<RankBean.ContentBean> rankBeans);
    }

    interface Presenter extends BasePresenter<RankingContract.View> {

        void getRank();

        void updateRank(RankingAdapter adapter, List<RankBean.ContentBean> rankBeans);

        void goRankingDetail(int position);
    }
}
