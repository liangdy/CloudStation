package com.cloud.ui.guide;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cloud.R;
import com.cloud.ui.BaseActivity;

/**
 * Project: CloudStation
 * FileName: GuideActivity.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 2/22/17 5:01 PM
 * Editor: ldy
 * Modify Date: 2/22/17 5:01 PM
 * Remark:
 */
public class GuideActivity extends BaseActivity {

    private static final String TAG = GuideActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int fragmentRoot() {
        return 0;
    }

    @Override
    protected String getPageName() {
        return TAG;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }
}
