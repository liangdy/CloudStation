package com.cloud.ui.music.detail.ranking;

import com.cloud.model.music.ContentBean;
import com.cloud.model.music.RankDetail;
import com.cloud.ui.BasePresenter;
import com.cloud.ui.BaseView;
import com.cloud.ui.adapter.RankingDetailAdapter;

import java.util.List;

/**
 * Project: CloudStation
 * FileName: RankingDetailContract.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/1/17 6:32 PM
 * Editor: ldy
 * Modify Date: 9/1/17 6:32 PM
 * Remark:
 */
public interface RankingDetailContract {
    interface View extends BaseView {

        void initialized();

        void loadRankingDetail();

        void loadSongList(List<ContentBean> contentBeans);

        void setRankingInfo(RankDetail.BillboardBean rankingInfo);

        void setToolBarMarginTop();

        void setToolBarBgAlpha(int alpha);

        void setToolBarTitle(String title);

        void setToolBarSubTitle(String subTitle);

        void setInfoAlpha(float alpha);

        void setOperationAlpha(float alpha);

        void initHeaderView();

        void setHeaderCount(int count);
    }

    interface Presenter extends BasePresenter<RankingDetailContract.View> {

        void getRankingDetail(String type);

        void onBack();

        void doSearch();

        void setToolBarStatus(int offset, int maxScroll);

        void updateRanking(RankingDetailAdapter adapter, List<ContentBean> contentBeans);
    }
}
