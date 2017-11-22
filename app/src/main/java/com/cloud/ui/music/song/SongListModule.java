package com.cloud.ui.music.song;

import com.cloud.model.music.AllSongSheet;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Project: CloudStation
 * FileName: MusicHomeModule.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/3/17 10:23 AM
 * Editor: ldy
 * Modify Date: 3/3/17 10:23 AM
 * Remark:
 */
@Module
public class SongListModule {

    @Provides
    List<AllSongSheet.ContentBean> providesSongList() {
        return new ArrayList<>();
    }
}
