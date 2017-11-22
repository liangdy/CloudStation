package com.cloud.ui.music.ranking;

import com.cloud.model.music.RankBean;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Project: CloudStation
 * FileName: RankingModule.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/13/17 5:37 PM
 * Editor: ldy
 * Modify Date: 8/13/17 5:37 PM
 * Remark:
 */
@Module
public class RankingModule {

    @Provides
    List<RankBean.ContentBean> providesRankBean() {
        return new ArrayList<>();
    }
}
