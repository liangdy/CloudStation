package com.cloud.ui.music.local.song;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cloud.R;
import com.cloud.model.music.MusicInfo;
import com.cloud.ui.BaseFragment;
import com.cloud.ui.adapter.LocalSongAdapter;
import com.cloud.view.SideBar;
import com.magical.library.utils.ToastUtils;
import com.magical.library.utils.UiUtils;
import com.magical.library.view.recycler.MagicalRecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Project: CloudStation
 * FileName: LocalSongFragment.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/4/17 10:25 AM
 * Editor: ldy
 * Modify Date: 9/4/17 10:25 AM
 * Remark:
 */
public class LocalSongFragment extends BaseFragment implements LocalSongContract.View {

    private static final String TAG = LocalSongFragment.class.getSimpleName();

    @BindView(R.id.song_list)
    MagicalRecyclerView songList;
    @BindView(R.id.dialog_text)
    TextView dialogText;
    @BindView(R.id.sidebar)
    SideBar sidebar;

    @Inject
    LocalSongPresenter mPresenter;
    @Inject
    LocalSongAdapter mAdapter;

    private View header;
    private ViewHolder headerHolder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initInjector() {
        DaggerLocalSongComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .localSongModule(new LocalSongModule())
                .build()
                .inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_local_song;
    }

    @Override
    public void initialized() {
        songList.setAdapter(mAdapter);
        songList.setHasFixedSize(true);
        sidebar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                dialogText.setText(s);
                sidebar.setView(dialogText);
                /*if (positionMap.get(s) != null) {
                    int i = positionMap.get(s);
                    ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(i + 1, 0);
                }*/

            }
        });
        songList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    sidebar.setVisibility(View.VISIBLE);
                }
            }
        });

        mAdapter.setListener(new LocalSongAdapter.ActionListener() {
            @Override
            public void actionMore(MusicInfo model) {

            }

            @Override
            public void onItemClick(MusicInfo model) {

            }
        });
    }

    @Override
    public void initHeaderView() {
        header = View.inflate(getActivity(), R.layout.header_song, null);
        headerHolder = new ViewHolder(header, getActivity());
        header.setLayoutParams(new MagicalRecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UiUtils.dp2px(getActivity(), 48)));
        songList.addHeaderView(header);
    }

    @Override
    public void setHeaderCount(int count) {
        headerHolder.songCountTv.setText(getString(R.string.detail_song_count, String.valueOf(count)));
    }

    @Override
    public void initData() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void loadLocalSong() {
        if (mPresenter != null) {
            mPresenter.getLocalSong();
        }
    }

    @Override
    public void updateLocalSong(List<MusicInfo> musicInfos) {
        if (mAdapter != null && mPresenter != null) {
            mPresenter.updateLocalSong(mAdapter, musicInfos);
        }
    }

    @Override
    public String getPageName() {
        return TAG;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    static class ViewHolder {
        @BindView(R.id.song_count_iv)
        TextView songCountTv;
        @BindView(R.id.edit_iv)
        ImageView editIv;
        @BindView(R.id.play_iv)
        ImageView playIv;
        @BindView(R.id.item)
        RelativeLayout item;

        private Context context;

        ViewHolder(View view, Context context) {
            ButterKnife.bind(this, view);
            this.context = context;
        }

        @OnClick({R.id.edit_iv, R.id.item, R.id.play_iv})
        void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.edit_iv:
                    ToastUtils.show(context, "edit");
                    break;
                case R.id.item:
                    ToastUtils.show(context, "item_click");
                    break;
                case R.id.play_iv:
                    ToastUtils.show(context, "item_play");
                    break;
                default:
                    break;
            }
        }
    }
}
