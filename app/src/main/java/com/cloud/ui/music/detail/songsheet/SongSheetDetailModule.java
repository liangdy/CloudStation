package com.cloud.ui.music.detail.songsheet;

import com.cloud.model.music.ContentBean;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Project: CloudStation
 * FileName: SongSheetDetailModule.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/22/17 5:03 PM
 * Editor: ldy
 * Modify Date: 8/22/17 5:03 PM
 * Remark:
 */
@Module
public class SongSheetDetailModule {

    @Provides
    List<ContentBean> providesSongSheetDetail() {
        return new ArrayList<>();
    }
}
