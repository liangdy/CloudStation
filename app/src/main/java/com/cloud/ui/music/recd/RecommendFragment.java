package com.cloud.ui.music.recd;

import android.view.View;
import android.view.ViewGroup;

import com.cloud.R;
import com.cloud.model.music.Album;
import com.cloud.model.music.Banner;
import com.cloud.model.music.Music;
import com.cloud.model.music.RecdList;
import com.cloud.model.music.SongSheet;
import com.cloud.ui.BaseFragment;
import com.cloud.ui.adapter.RecommendAdapter;
import com.cloud.view.BannerView;
import com.magical.library.utils.UiUtils;
import com.magical.library.view.recycler.MagicalRecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Project: CloudStation
 * FileName: RecommendSongFragment.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/3/17 3:31 PM
 * Editor: ldy
 * Modify Date: 3/3/17 3:31 PM
 * Remark:
 */
public class RecommendFragment extends BaseFragment implements RecommendContract.View {

    private static final String TAG = RecommendFragment.class.getSimpleName();

    @Inject
    RecommendPresenter mPresenter;
    BannerView bannerView;
    @BindView(R.id.recd_list)
    MagicalRecyclerView recdList;

    @Inject
    RecommendAdapter mAdapter;

    @Override
    protected void initInjector() {
        DaggerRecommendComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .recommendModule(new RecommendModule())
                .build()
                .inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recd_song;
    }

    @Override
    public void initData() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void initHeader() {
        bannerView = (BannerView) View.inflate(getActivity(), R.layout.header_recommend, null);
        bannerView.setLayoutParams(new MagicalRecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UiUtils.dp2px(getActivity(), 95)));
        recdList.addHeaderView(bannerView);
    }

    @Override
    public void loadData() {
        if (mPresenter != null) {
            mPresenter.getBanner();
            mPresenter.getRecdSongSheet();
            mPresenter.getRecdAlbum();
            mPresenter.getRecdMusic();
        }
    }

    @Override
    public String getPageName() {
        return TAG;
    }

    @Override
    public void initialized() {
        recdList.setAdapter(mAdapter);
        mAdapter.setListener(new RecommendAdapter.ActionListener() {
            @Override
            public void clickHotSongSheet(SongSheet.SongSheetBean songSheetBean) {
                if (mPresenter != null) {
                    mPresenter.goSongSheetDetail(songSheetBean.listid);
                }
            }

            @Override
            public void clickRecdAlbum(Album.ListBean albumBean) {
                if (mPresenter != null) {
                    mPresenter.goAlbumDetail(albumBean.album_id);
                }
            }

            @Override
            public void clickDailyRecdMusic(Music.SongListBean songListBean) {
                if (mPresenter != null) {
                    mPresenter.play(songListBean);
                }
            }

            @Override
            public void clickMore() {
                if (mPresenter != null) {
                    mPresenter.actionMore();
                }
            }
        });
    }

    @Override
    public void showLoadProgress() {

    }

    @Override
    public void closeLoadProgress() {

    }

    @Override
    public void updateRecdData(List<RecdList> recdLists) {
        if (mPresenter != null && mAdapter != null) {
            mPresenter.updateRecdData(mAdapter, recdLists);
        }
    }

    @Override
    public void loadBanner(Banner banner) {
        if (mPresenter != null) {
            mPresenter.loadBanner(banner, bannerView);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
