package com.cloud.ui.music;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.cloud.R;
import com.cloud.ui.BaseFragment;
import com.cloud.ui.adapter.MusicHomePagerAdapter;
import com.magical.library.view.smartlayout.SmartTabLayout;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Project: CloudStation
 * FileName: MusicHomeFragment.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/2/17 1:33 PM
 * Editor: ldy
 * Modify Date: 3/2/17 1:33 PM
 * Remark:
 */
public class MusicHomeFragment extends BaseFragment implements MusicHomeContract.View {

    private static final String TAG = MusicHomeFragment.class.getSimpleName();

    @BindView(R.id.tabs)
    SmartTabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Inject
    MusicHomePresenter mPresenter;
    @Inject
    MusicHomePagerAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initInjector() {
        DaggerMusicHomeComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .musicHomeModule(new MusicHomeModule(getBaseActivity()))
                .build()
                .inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_music_home;
    }

    @Override
    public void initData() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public String getPageName() {
        return TAG;
    }

    @Override
    public void initialized() {
        setupViewPager();
    }

    private void setupViewPager() {
        viewpager.setAdapter(mAdapter);
        viewpager.setOffscreenPageLimit(4);
        tabs.setViewPager(viewpager);
        viewpager.setCurrentItem(0);
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
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
