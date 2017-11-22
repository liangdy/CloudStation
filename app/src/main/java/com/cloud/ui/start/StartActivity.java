package com.cloud.ui.start;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.FrameLayout;

import com.cloud.R;
import com.cloud.ui.BaseActivity;
import com.magical.library.utils.ScreenUtils;
import com.magical.library.utils.StatusBarUtils;
import com.magical.library.view.XImageView;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Project: CloudStation
 * FileName: StartActivity.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 2/22/17 4:37 PM
 * Editor: ldy
 * Modify Date: 2/22/17 4:37 PM
 * Remark:
 */
public class StartActivity extends BaseActivity implements StartContract.View {

    private static final String TAG = StartActivity.class.getSimpleName();

    @Inject
    StartPresenter mPresenter;
    @BindView(R.id.app_start_img)
    XImageView mAppStartImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initInjector() {
        DaggerStartComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .startModule(new StartModule())
                .build()
                .inject(this);
    }

    @Override
    protected void initUI() {
        StatusBarUtils.setStatusBarFromImg(StartActivity.this, ContextCompat
                .getColor(this, R.color.transparent));
        int screenW = ScreenUtils.getScreenWidth(this);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mAppStartImg
                .getLayoutParams();
        layoutParams.width = screenW;
        layoutParams.height = (int) (screenW * 1.7778);
        mAppStartImg.setLayoutParams(layoutParams);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
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
        return R.layout.activity_start;
    }

    @OnClick(R.id.app_start_img)
    public void onClick() {
        if (mPresenter != null) {
            mPresenter.onStartClick();
        }
    }

    @Override
    public void loadImageView(File file) {
        if (mPresenter != null) {
            mPresenter.showStartPage(file, mAppStartImg);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
