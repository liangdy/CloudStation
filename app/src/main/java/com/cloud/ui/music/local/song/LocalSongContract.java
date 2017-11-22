package com.cloud.ui.music.local.song;

import com.cloud.model.music.MusicInfo;
import com.cloud.ui.BasePresenter;
import com.cloud.ui.BaseView;
import com.cloud.ui.adapter.LocalSongAdapter;

import java.util.List;

/**
 * Project: CloudStation
 * FileName: LocalSongContract.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/4/17 10:26 AM
 * Editor: ldy
 * Modify Date: 9/4/17 10:26 AM
 * Remark:
 */
public interface LocalSongContract {

    interface View extends BaseView {

        void initialized();
        
        void initHeaderView();

        void loadLocalSong();

        void updateLocalSong(List<MusicInfo> musicInfos);

        void setHeaderCount(int count);
    }

    interface Presenter extends BasePresenter<LocalSongContract.View> {

        void getLocalSong();

        void updateLocalSong(LocalSongAdapter adapter, List<MusicInfo> musicInfos);
    }

}
