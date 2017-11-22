package com.cloud.ui.music.local;

import android.support.v4.app.FragmentManager;

import com.cloud.R;
import com.cloud.ui.BaseActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Project: CloudStation
 * FileName: LocalMusicModule.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/3/17 4:33 PM
 * Editor: ldy
 * Modify Date: 9/3/17 4:33 PM
 * Remark:
 */
@Module
public class LocalMusicModule {

    private BaseActivity mActivity;

    public LocalMusicModule(BaseActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    String[] providesTitles() {
        String[] titles = mActivity.getResources().getStringArray(R.array.local_music_array);
        return titles;
    }

    @Provides
    FragmentManager providesFragmentManager() {
        return mActivity.getSupportFragmentManager();
    }
}
