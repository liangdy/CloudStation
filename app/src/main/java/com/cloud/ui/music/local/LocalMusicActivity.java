package com.cloud.ui.music.local;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cloud.R;
import com.cloud.ui.BaseActivity;
import com.cloud.ui.adapter.LocalMusicPagerAdapter;
import com.magical.library.utils.StatusBarUtils;
import com.magical.library.view.smartlayout.SmartTabLayout;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Project: CloudStation
 * FileName: LocalMusicActivity.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/3/17 4:32 PM
 * Editor: ldy
 * Modify Date: 9/3/17 4:32 PM
 * Remark:
 */
public class LocalMusicActivity extends BaseActivity implements LocalMusicContract.View {

    private static final String TAG = LocalMusicActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    SmartTabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Inject
    LocalMusicPresenter mPresenter;

    @Inject
    LocalMusicPagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setColor(this, getResources().getColor(R.color.green), 0);
    }

    @Override
    protected void initInjector() {
        DaggerLocalMusicComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .localMusicModule(new LocalMusicModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initUI() {
        if (null != toolbar) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.actionbar_back);
            setTitle(getString(R.string.local_music));
        }
    }

    @Override
    public void initialized() {
        viewpager.setAdapter(mAdapter);
        viewpager.setOffscreenPageLimit(4);
        tabs.setViewPager(viewpager);
        int tab = getIntent().getIntExtra("tab", 0);
        viewpager.setCurrentItem(tab);
        tabs.setOnPageChangeListener(pageChangeListener);
    }

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void initListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPresenter != null) {
                    mPresenter.onBack();
                }
            }
        });
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
        return R.layout.activity_local_music;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
