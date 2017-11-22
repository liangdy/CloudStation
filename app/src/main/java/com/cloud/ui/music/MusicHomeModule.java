package com.cloud.ui.music;

import android.support.v4.app.FragmentManager;

import com.cloud.R;
import com.cloud.ui.BaseActivity;

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
public class MusicHomeModule {

    private BaseActivity mActivity;

    public MusicHomeModule(BaseActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    String[] providesTitles() {
        String[] titles = mActivity.getResources().getStringArray(R.array.music_array);
        return titles;
    }

    @Provides
    FragmentManager providesFragmentManager() {
        return mActivity.getSupportFragmentManager();
    }
}
