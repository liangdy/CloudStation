package com.cloud.ui.music.local.artist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cloud.R;
import com.cloud.model.music.ArtistInfo;
import com.cloud.ui.BaseFragment;
import com.cloud.ui.adapter.LocalArtistAdapter;
import com.cloud.view.SideBar;
import com.magical.library.imageloader.ImageLoader;
import com.magical.library.utils.EncryptUtils;
import com.magical.library.view.XImageView;
import com.magical.library.view.recycler.MagicalRecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Project: CloudStation
 * FileName: LocalSingerFragment.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/4/17 3:20 PM
 * Editor: ldy
 * Modify Date: 9/4/17 3:20 PM
 * Remark:
 */
public class LocalArtistFragment extends BaseFragment implements LocalArtistContract.View {

    private static final String TAG = LocalArtistFragment.class.getSimpleName();

    @BindView(R.id.artist_list)
    MagicalRecyclerView artistList;
    @BindView(R.id.dialog_text)
    TextView dialogText;
    @BindView(R.id.sidebar)
    SideBar sidebar;

    @Inject
    LocalArtistPresenter mPresenter;
    @Inject
    LocalArtistAdapter mAdapter;

    @Override
    protected void initInjector() {
        DaggerLocalArtistComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .localArtistModule(new LocalArtistModule())
                .build()
                .inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_local_artist;
    }

    @Override
    public void initialized() {
        artistList.setAdapter(mAdapter);
        artistList.setHasFixedSize(true);
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
        artistList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    sidebar.setVisibility(View.VISIBLE);
                }
            }
        });

        mAdapter.setListener(new LocalArtistAdapter.ActionListener() {

            @Override
            public void actionMore(ArtistInfo model) {

            }

            @Override
            public void onItemClick(ArtistInfo model) {

            }

            @Override
            public void loadImage(XImageView iv, String artist) {
                if (mPresenter != null) {
                    mPresenter.loadImage(iv, artist);
                }
            }
        });
    }

    @Override
    public void initData() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void loadArtistInfo() {
        if (mPresenter != null) {
            mPresenter.getArtistInfo();
        }
    }

    @Override
    public void updateArtistInfo(List<ArtistInfo> artistInfos) {
        if (mPresenter != null) {
            mPresenter.updateArtistInfo(mAdapter, artistInfos);
        }
    }

    @Override
    public void updateImage(XImageView iv, String url) {
        ImageLoader.loadImage(iv, url, EncryptUtils.getMD5(url));
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
