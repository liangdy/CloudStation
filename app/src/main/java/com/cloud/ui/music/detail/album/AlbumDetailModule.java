package com.cloud.ui.music.detail.album;

import com.cloud.model.music.AlbumDetail;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Project: CloudStation
 * FileName: AlbumModule.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/29/17 4:35 PM
 * Editor: ldy
 * Modify Date: 8/29/17 4:35 PM
 * Remark:
 */
@Module
public class AlbumDetailModule {

    @Provides
    List<AlbumDetail.SongListBean> providesAlbumSongList() {
        return new ArrayList<>();
    }
}
