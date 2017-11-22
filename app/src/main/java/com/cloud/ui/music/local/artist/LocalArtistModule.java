package com.cloud.ui.music.local.artist;

import com.cloud.model.music.ArtistInfo;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Project: CloudStation
 * FileName: LocalSingerModule.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/4/17 3:21 PM
 * Editor: ldy
 * Modify Date: 9/4/17 3:21 PM
 * Remark:
 */
@Module
public class LocalArtistModule {
    @Provides
    List<ArtistInfo> providesArtistInfo() {
        return new ArrayList<>();
    }
}
