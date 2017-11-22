package com.cloud.ui.music.recd;

import com.cloud.model.music.RecdList;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Project: CloudStation
 * FileName: RecdSongModule.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/10/17 2:40 PM
 * Editor: ldy
 * Modify Date: 3/10/17 2:40 PM
 * Remark:
 */
@Module
public class RecommendModule {

    @Provides
    List<RecdList> providesSongRecd() {
        return new ArrayList<>();
    }
}
