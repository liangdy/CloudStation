package com.cloud.ui.music.local.artist;

import com.cloud.model.music.ArtistInfo;
import com.cloud.ui.BasePresenter;
import com.cloud.ui.BaseView;
import com.cloud.ui.adapter.LocalArtistAdapter;
import com.magical.library.view.XImageView;

import java.util.List;

/**
 * Project: CloudStation
 * FileName: LocalSingerContract.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/4/17 3:21 PM
 * Editor: ldy
 * Modify Date: 9/4/17 3:21 PM
 * Remark:
 */
public interface LocalArtistContract {

    interface View extends BaseView {

        void initialized();

        void loadArtistInfo();

        void updateArtistInfo(List<ArtistInfo> artistInfos);

        void updateImage(XImageView iv, String url);
    }

    interface Presenter extends BasePresenter<LocalArtistContract.View> {

        void getArtistInfo();

        void updateArtistInfo(LocalArtistAdapter adapter, List<ArtistInfo> artistInfos);

        void loadImage(XImageView iv, String artist);
    }
}
