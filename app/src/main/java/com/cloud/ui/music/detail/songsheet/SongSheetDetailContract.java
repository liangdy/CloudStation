package com.cloud.ui.music.detail.songsheet;

import com.cloud.model.music.ContentBean;
import com.cloud.model.music.SongSheetDetail;
import com.cloud.ui.BasePresenter;
import com.cloud.ui.BaseView;
import com.cloud.ui.adapter.SongSheetDetailAdapter;

import java.util.List;

/**
 * Project: CloudStation
 * FileName: SongSheetDetailContract.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/22/17 5:03 PM
 * Editor: ldy
 * Modify Date: 8/22/17 5:03 PM
 * Remark:
 */
public interface SongSheetDetailContract {

    interface View extends BaseView {

        void initialized();

        void loadSongSheetDetail();

        void loadSongList(List<ContentBean> contentBeans);

        void setSongSheetInfo(SongSheetDetail songSheetInfo);

        void setToolBarMarginTop();

        void setToolBarBgAlpha(int alpha);

        void setToolBarTitle(String title);

        void setToolBarSubTitle(String subTitle);

        void setInfoAlpha(float alpha);

        void setOperationAlpha(float alpha);

        void initHeaderView();

        void setHeaderCount(int count);
    }

    interface Presenter extends BasePresenter<SongSheetDetailContract.View> {

        void getSongSheetDetail(String listid);

        void onBack();

        void doSearch();

        void setToolBarStatus(int offset, int maxScroll);

        void updateSongSheet(SongSheetDetailAdapter adapter, List<ContentBean> contentBeans);
    }
}
