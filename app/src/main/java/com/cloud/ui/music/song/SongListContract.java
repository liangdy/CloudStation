package com.cloud.ui.music.song;

import com.cloud.model.music.AllSongSheet;
import com.cloud.model.music.SongSheetCategory;
import com.cloud.ui.BasePresenter;
import com.cloud.ui.BaseView;
import com.cloud.ui.adapter.AllSongSheetAdapter;

import java.util.List;

/**
 * Project: CloudStation
 * FileName: MusicHomeContract.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/3/17 10:26 AM
 * Editor: ldy
 * Modify Date: 3/3/17 10:26 AM
 * Remark:
 */
public interface SongListContract {

    interface View extends BaseView {
        void initialized();

        void showLoadProgress();

        void closeLoadProgress();

        void loadComplete();

        void loadAllSongSheet(List<AllSongSheet.ContentBean> allSongSheets);

        void loadSongSheetCategory(List<SongSheetCategory.ContentBean> songSheetCategories);
    }

    interface Presenter extends BasePresenter<SongListContract.View> {

        void refresh();

        void loadMore();

        void getAllSongSheet(int num);

        void updateSongSheet(AllSongSheetAdapter mAdapter, List<AllSongSheet.ContentBean> contentBeen);

        void getAllSongCategory();

        void updateSongCategory(List<SongSheetCategory.ContentBean> songSheetCategories);

        void goSongSheetDetail(int position);
    }
}
