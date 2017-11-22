package com.cloud.ui.video;

import android.os.Bundle;

import com.cloud.ui.BaseFragment;

/**
 * Project: CloudStation
 * FileName: VideoHomeFragment.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/2/17 2:12 PM
 * Editor: ldy
 * Modify Date: 3/2/17 2:12 PM
 * Remark:
 */
public class VideoHomeFragment extends BaseFragment {

    private static final String TAG = VideoHomeFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initInjector() {

    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initData() {

    }

    @Override
    public String getPageName() {
        return TAG;
    }
}
