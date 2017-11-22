package com.cloud.ui.music.local.album;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cloud.R;
import com.cloud.model.music.AlbumInfo;
import com.cloud.ui.BaseFragment;
import com.cloud.ui.adapter.LocalAlbumAdapter;
import com.cloud.view.SideBar;
import com.magical.library.view.recycler.MagicalRecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Project: CloudStation
 * FileName: LocalAlbumFragment.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/5/17 7:40 PM
 * Editor: ldy
 * Modify Date: 9/5/17 7:40 PM
 * Remark:
 */
public class LocalAlbumFragment extends BaseFragment implements LocalAlbumContract.View {

    private static final String TAG = LocalAlbumFragment.class.getSimpleName();

    @Inject
    LocalAlbumPresenter mPresenter;
    @Inject
    LocalAlbumAdapter mAdapter;
    @BindView(R.id.album_list)
    MagicalRecyclerView albumList;
    @BindView(R.id.dialog_text)
    TextView dialogText;
    @BindView(R.id.sidebar)
    SideBar sidebar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initInjector() {
        DaggerLocalAlbumComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .localAlbumModule(new LocalAlbumModule())
                .build()
                .inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_local_album;
    }

    @Override
    public void initData() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void initialized() {
        albumList.setAdapter(mAdapter);
        albumList.setHasFixedSize(true);
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
        albumList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    sidebar.setVisibility(View.VISIBLE);
                }
            }
        });
        mAdapter.setListener(new LocalAlbumAdapter.ActionListener() {
            @Override
            public void actionMore(AlbumInfo model) {

            }

            @Override
            public void onItemClick(AlbumInfo model) {

            }
        });
    }

    @Override
    public void loadAlbumInfo() {
        if (mPresenter != null) {
            mPresenter.getAlbumInfo();
        }
    }

    @Override
    public void updateAlbumInfo(List<AlbumInfo> albumInfos) {
        if (mPresenter != null) {
            mPresenter.updateAlbumInfo(mAdapter, albumInfos);
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
}
