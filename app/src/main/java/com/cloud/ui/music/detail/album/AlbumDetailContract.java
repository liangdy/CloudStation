package com.cloud.ui.music.detail.album;

import com.cloud.model.music.AlbumDetail;
import com.cloud.model.music.AlbumInfo;
import com.cloud.ui.BasePresenter;
import com.cloud.ui.BaseView;
import com.cloud.ui.adapter.AlbumDetailAdapter;

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
public interface AlbumDetailContract {

    interface View extends BaseView {

        void initialized();

        void loadAlbumDetail();

        void loadSongList(List<AlbumDetail.SongListBean> songListBeans);

        void setAlbumInfo(AlbumInfo albumInfo);

        void setToolBarMarginTop();

        void setToolBarBgAlpha(int alpha);

        void setToolBarTitle(String title);

        void setToolBarSubTitle(String subTitle);

        void setInfoAlpha(float alpha);

        void setOperationAlpha(float alpha);

        void initHeaderView();

        void setHeaderCount(int count);
    }

    interface Presenter extends BasePresenter<AlbumDetailContract.View> {

        void getAlbumDetail(String albumId);

        void onBack();

        void doSearch();

        void setToolBarStatus(int offset, int maxScroll);

        void updateAlbum(AlbumDetailAdapter adapter, List<AlbumDetail.SongListBean> songListBeans);
    }
}
