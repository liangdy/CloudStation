package com.cloud.ui.music.detail.ranking;

import com.cloud.model.music.ContentBean;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Project: CloudStation
 * FileName: RankingDetailModule.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/1/17 6:28 PM
 * Editor: ldy
 * Modify Date: 9/1/17 6:28 PM
 * Remark:
 */
@Module
public class RankingDetailModule {
    @Provides
    List<ContentBean> providesRankingSongList() {
        return new ArrayList<>();
    }
}
