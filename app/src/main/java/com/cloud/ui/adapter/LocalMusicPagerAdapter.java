package com.cloud.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cloud.ui.music.local.album.LocalAlbumFragment;
import com.cloud.ui.music.local.artist.LocalArtistFragment;
import com.cloud.ui.music.local.folder.LocalFolderFragment;
import com.cloud.ui.music.local.song.LocalSongFragment;

import javax.inject.Inject;

/**
 * Project: CloudStation
 * FileName: LocalMusicPagerAdapter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/3/17 3:05 PM
 * Editor: ldy
 * Modify Date: 3/3/17 3:05 PM
 * Remark:
 */
public class LocalMusicPagerAdapter extends FragmentPagerAdapter {

    String[] titles;

    @Inject
    public LocalMusicPagerAdapter(FragmentManager fm, String[] titles) {
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
                fragment = new LocalSongFragment();
                break;
            case 1:
                fragment = new LocalArtistFragment();
                break;
            case 2:
                fragment = new LocalAlbumFragment();
                break;
            case 3:
                fragment = new LocalFolderFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }
}
