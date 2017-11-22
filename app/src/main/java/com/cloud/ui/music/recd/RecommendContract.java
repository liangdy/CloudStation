package com.cloud.ui.music.recd;

import com.cloud.model.music.Banner;
import com.cloud.model.music.Music;
import com.cloud.model.music.RecdList;
import com.cloud.ui.BasePresenter;
import com.cloud.ui.BaseView;
import com.cloud.ui.adapter.RecommendAdapter;
import com.cloud.view.BannerView;

import java.util.List;

/**
 * Project: CloudStation
 * FileName: RecdSongContract.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/10/17 2:41 PM
 * Editor: ldy
 * Modify Date: 3/10/17 2:41 PM
 * Remark:
 */
public interface RecommendContract {

    interface View extends BaseView {

        void initHeader();

        void loadData();

        void showLoadProgress();

        void closeLoadProgress();

        void initialized();

        void updateRecdData(List<RecdList> recdLists);

        void loadBanner(Banner banner);
    }

    interface Presenter extends BasePresenter<RecommendContract.View> {
        void getBanner();

        void getRecdSongSheet();

        void getRecdAlbum();

        void getRecdMusic();

        void loadBanner(Banner banner, BannerView bannerView);

        void updateRecdData(RecommendAdapter mAdapter, List<RecdList> recdLists);

        void goSongSheetDetail(String listId);

        void goAlbumDetail(String albumId);
        
        void play(Music.SongListBean songListBean);
        
        void actionMore();
    }
}
