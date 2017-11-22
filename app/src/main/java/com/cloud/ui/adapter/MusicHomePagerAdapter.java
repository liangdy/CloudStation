package com.cloud.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cloud.ui.music.manager.ManagerFragment;
import com.cloud.ui.music.ranking.RankingFragment;
import com.cloud.ui.music.recd.RecommendFragment;
import com.cloud.ui.music.song.SongListFragment;

import javax.inject.Inject;

/**
 * Project: CloudStation
 * FileName: MusicHomePagerAdapter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/3/17 3:05 PM
 * Editor: ldy
 * Modify Date: 3/3/17 3:05 PM
 * Remark:
 */
public class MusicHomePagerAdapter extends FragmentPagerAdapter {

    String[] titles;

    @Inject
    public MusicHomePagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
        if (titles == null) {
            titles = new String[]{};
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new RecommendFragment();
                break;
            case 1:
                fragment = new SongListFragment();
                break;
            case 2:
                fragment = new RankingFragment();
                break;
            case 3:
                fragment = new ManagerFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }
}
