package com.cloud.ui.music.song;

import android.graphics.Color;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cloud.R;
import com.cloud.model.music.AllSongSheet;
import com.cloud.model.music.SongSheetCategory;
import com.cloud.ui.BaseFragment;
import com.cloud.ui.adapter.AllSongSheetAdapter;
import com.magical.library.utils.UiUtils;
import com.magical.library.view.recycler.CustomLoadMoreView;
import com.magical.library.view.recycler.MagicalRecyclerView;
import com.magical.library.view.recycler.MagicalRefreshRecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Project: CloudStation
 * FileName: SongListFragment.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/3/17 3:32 PM
 * Editor: ldy
 * Modify Date: 3/3/17 3:32 PM
 * Remark:
 */
public class SongListFragment extends BaseFragment implements SongListContract.View {

    private static final String TAG = SongListFragment.class.getSimpleName();

    @BindView(R.id.refresh_view)
    MagicalRefreshRecyclerView refreshView;
    MagicalRecyclerView recyclerView;

    @Inject
    SongListPresenter mPresenter;
    @Inject
    AllSongSheetAdapter mAdapter;
    @BindView(R.id.category_tv)
    TextView categoryTv;
    @BindView(R.id.select_category_btn)
    Button selectCategoryBtn;

    @Override
    protected void initInjector() {
        DaggerSongListComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .songListModule(new SongListModule())
                .build()
                .inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_song_list;
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
        refreshView.setLoadMoreView(new CustomLoadMoreView(getActivity()));
        refreshView.setColorSchemeColors(Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN);
        refreshView.setLoadMoreEnabled(true);
        recyclerView = refreshView.getMagicalRecyclerView();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setPadding(UiUtils.dp2px(getActivity(), 10), 0, UiUtils.dp2px(getActivity(), 10), 0);
        recyclerView.setVerticalScrollBarEnabled(false);
        recyclerView.setAdapter(mAdapter);
        refreshView.setOnPullRefreshListener(new MagicalRefreshRecyclerView.OnPullRefreshListener() {
            @Override
            public void onPullRefresh() {
                if (mPresenter != null) {
                    mPresenter.refresh();
                }
            }
        });
        refreshView.setOnLoadMoreListener(new MagicalRefreshRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (mPresenter != null) {
                    mPresenter.loadMore();
                }
            }
        });
        recyclerView.setOnItemClickListener(new MagicalRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(MagicalRecyclerView magicalRecyclerView, View view, int position) {
                if (mPresenter != null) {
                    mPresenter.goSongSheetDetail(position);
                }
            }
        });
    }

    @Override
    public void loadAllSongSheet(List<AllSongSheet.ContentBean> allSongSheets) {
        if (mPresenter != null && mAdapter != null) {
            mPresenter.updateSongSheet(mAdapter, allSongSheets);
        }
    }

    @Override
    public void loadSongSheetCategory(List<SongSheetCategory.ContentBean> songSheetCategories) {
        if (mPresenter != null) {
            mPresenter.updateSongCategory(songSheetCategories);
        }
    }

    @Override
    public void loadComplete() {
        refreshView.pullRefreshComplete();
        refreshView.loadMoreComplete();
    }

    @Override
    public void showLoadProgress() {

    }

    @Override
    public void closeLoadProgress() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (recyclerView != null) {
            recyclerView.destroyDrawingCache();
        }
    }

    @OnClick(R.id.select_category_btn)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.select_category_btn:
                if (mPresenter != null) {
                    mPresenter.getAllSongCategory();
                }
                break;
            default:
                break;
        }
    }
}
