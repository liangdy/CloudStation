package com.cloud.ui.music.play;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.cloud.R;
import com.cloud.ui.BaseActivity;
import com.cloud.view.PlayerSeekBar;
import com.cloud.view.XViewPager;
import com.cloud.view.lrc.LrcView;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Project: CloudStation
 * FileName: PlayActivity.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/3/17 2:55 PM
 * Editor: ldy
 * Modify Date: 9/3/17 2:55 PM
 * Remark:
 */
public class PlayActivity extends BaseActivity implements PlayContract.View {

    private static final String TAG = PlayActivity.class.getSimpleName();

    @Inject
    PlayPresenter mPresenter;
    @BindView(R.id.album_art_iv)
    ImageView albumArtIv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.view_pager)
    XViewPager viewPager;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.needle)
    ImageView needle;
    @BindView(R.id.headerView)
    FrameLayout headerView;
    @BindView(R.id.volume_seek)
    SeekBar volumeSeek;
    @BindView(R.id.volume_layout)
    LinearLayout volumeLayout;
    @BindView(R.id.tragetlrc)
    TextView tragetlrc;
    @BindView(R.id.lrc_view)
    LrcView lrcView;
    @BindView(R.id.lrcviewContainer)
    RelativeLayout lrcviewContainer;
    @BindView(R.id.playing_fav)
    ImageView playingFav;
    @BindView(R.id.playing_down)
    ImageView playingDown;
    @BindView(R.id.playing_cmt)
    ImageView playingCmt;
    @BindView(R.id.playing_more)
    ImageView playingMore;
    @BindView(R.id.music_tool)
    LinearLayout musicTool;
    @BindView(R.id.music_duration_played)
    TextView musicDurationPlayed;
    @BindView(R.id.play_seek)
    PlayerSeekBar playSeek;
    @BindView(R.id.music_duration)
    TextView musicDuration;
    @BindView(R.id.playing_mode)
    ImageView playingMode;
    @BindView(R.id.playing_pre)
    ImageView playingPre;
    @BindView(R.id.playing_play)
    ImageView playingPlay;
    @BindView(R.id.playing_next)
    ImageView playingNext;
    @BindView(R.id.playing_playlist)
    ImageView playingPlaylist;
    @BindView(R.id.bottom_control)
    LinearLayout bottomControl;

    private ActionBar ab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerPlayComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .playModule(new PlayModule())
                .build()
                .inject(this);
    }

    @Override
    protected void initUI() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ab = getSupportActionBar();
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setHomeAsUpIndicator(R.drawable.actionbar_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.onBack();
                }
            });
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter.attachView(this);
    }

    @Override
    public void initialized() {

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
        return R.layout.activity_play;
    }
}
