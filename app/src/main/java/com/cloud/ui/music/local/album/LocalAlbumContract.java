package com.cloud.ui.music.local.album;

import com.cloud.model.music.AlbumInfo;
import com.cloud.ui.BasePresenter;
import com.cloud.ui.BaseView;
import com.cloud.ui.adapter.LocalAlbumAdapter;

import java.util.List;

/**
 * Project: CloudStation
 * FileName: LocalAlbumContract.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/5/17 7:43 PM
 * Editor: ldy
 * Modify Date: 9/5/17 7:43 PM
 * Remark:
 */
public interface LocalAlbumContract {

    interface View extends BaseView {

        void initialized();

        void loadAlbumInfo();

        void updateAlbumInfo(List<AlbumInfo> albumInfos);
    }

    interface Presenter extends BasePresenter<LocalAlbumContract.View> {

        void getAlbumInfo();

        void updateAlbumInfo(LocalAlbumAdapter adapter, List<AlbumInfo> albumInfos);
    }
}
