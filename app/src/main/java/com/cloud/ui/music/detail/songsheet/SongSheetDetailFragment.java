package com.cloud.ui.music.detail.songsheet;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cloud.R;
import com.cloud.model.music.ContentBean;
import com.cloud.model.music.SongSheetDetail;
import com.cloud.ui.BaseFragment;
import com.cloud.ui.adapter.SongSheetDetailAdapter;
import com.cloud.utils.WindowAnimUtil;
import com.cloud.view.DrawableCenterTextView;
import com.magical.library.imageloader.ImageLoader;
import com.magical.library.utils.EncryptUtils;
import com.magical.library.utils.ScreenUtils;
import com.magical.library.utils.ToastUtils;
import com.magical.library.utils.UiUtils;
import com.magical.library.view.XImageView;
import com.magical.library.view.recycler.MagicalRecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Project: CloudStation
 * FileName: SongSheetDetailFragment.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/22/17 3:32 PM
 * Editor: ldy
 * Modify Date: 8/22/17 3:32 PM
 * Remark:
 */
public class SongSheetDetailFragment extends BaseFragment implements SongSheetDetailContract.View, AppBarLayout.OnOffsetChangedListener {

    public static final String TAG = SongSheetDetailFragment.class.getSimpleName();

    @Inject
    SongSheetDetailPresenter mPresenter;

    @BindView(R.id.detail_bg)
    XImageView detailBg;
    @BindView(R.id.back_iv)
    ImageView backIv;
    @BindView(R.id.more_iv)
    ImageView moreIv;
    @BindView(R.id.search_iv)
    ImageView searchIv;
    @BindView(R.id.detail_song_sheet_tv)
    TextView detailSongSheetTv;
    @BindView(R.id.tl_detail)
    Toolbar tlDetail;
    @BindView(R.id.detail_song_sheet_iv)
    XImageView detailSongSheetIv;
    @BindView(R.id.detail_listen_num_tv)
    TextView detailListenNumTv;
    @BindView(R.id.ll_detail_listen_num)
    LinearLayout llDetailListenNum;
    @BindView(R.id.rl_detail_song_sheet)
    RelativeLayout rlDetailSongSheet;
    @BindView(R.id.detail_song_sheet_title_tv)
    TextView detailSongSheetTitleTv;
    @BindView(R.id.detail_song_sheet_desc_tv)
    TextView detailSongSheetDescTv;
    @BindView(R.id.detail_song_sheet_tag_tv)
    TextView detailSongSheetTagTv;
    @BindView(R.id.ll_detail_info)
    LinearLayout llDetailInfo;
    @BindView(R.id.detail_collect_tv)
    DrawableCenterTextView detailCollectTv;
    @BindView(R.id.detail_comment_tv)
    DrawableCenterTextView detailCommentTv;
    @BindView(R.id.detail_share_tv)
    DrawableCenterTextView detailShareTv;
    @BindView(R.id.detail_download_tv)
    DrawableCenterTextView detailDownloadTv;
    @BindView(R.id.ll_detail_operation)
    LinearLayout llDetailOperation;
    @BindView(R.id.ll_detail_bottom)
    LinearLayout llDetailBottom;
    @BindView(R.id.ctl_detail)
    CollapsingToolbarLayout ctlDetail;
    @BindView(R.id.abl_detail)
    AppBarLayout ablDetail;
    @BindView(R.id.song_list)
    MagicalRecyclerView songList;

    @Inject
    WindowAnimUtil animUtil;
    @Inject
    SongSheetDetailAdapter mAdapter;

    private View header;
    private ViewHolder headerHolder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_song_sheet_detail;
    }

    @Override
    protected void initInjector() {
        DaggerSongSheetDetailComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .songSheetDetailModule(new SongSheetDetailModule())
                .build()
                .inject(this);
    }

    @Override
    public void initialized() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ablDetail.getLayoutParams();
            params.height = ScreenUtils.getStatusHeight(getActivity()) + UiUtils.dp2px(getActivity(), 254);
            ablDetail.setLayoutParams(params);

            int statusH = ScreenUtils.getStatusHeight(getActivity());
            FrameLayout.LayoutParams titleParams = (FrameLayout.LayoutParams) tlDetail
                    .getLayoutParams();
            titleParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
            titleParams.height = statusH + UiUtils.dp2px(getActivity(), 58);
            tlDetail.setLayoutParams(titleParams);
            tlDetail.setPadding(0, statusH, 0, 0);

            LinearLayout.LayoutParams infoParams = (LinearLayout.LayoutParams) llDetailInfo.getLayoutParams();
            infoParams.topMargin = statusH;
            llDetailInfo.setLayoutParams(infoParams);
        }

        ablDetail.addOnOffsetChangedListener(this);
        songList.setAdapter(mAdapter);
        mAdapter.setListener(new SongSheetDetailAdapter.ActionListener() {
            @Override
            public void actionMore(ContentBean bean) {

            }

            @Override
            public void itemClick(ContentBean bean) {

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
    public void loadSongSheetDetail() {
        String listid = getArguments().getString("listid");
        if (mPresenter != null && !TextUtils.isEmpty(listid)) {
            mPresenter.getSongSheetDetail(listid);
        }
    }

    @Override
    public void setSongSheetInfo(SongSheetDetail songSheetInfo) {
        ImageLoader.loadBlurImage(detailBg, songSheetInfo.pic_500, EncryptUtils.getMD5(songSheetInfo.pic_500), 60f);
        ImageLoader.loadImage(detailSongSheetIv, songSheetInfo.pic_300, EncryptUtils.getMD5(songSheetInfo.pic_300));
        detailSongSheetTitleTv.setText(R.string.song_sheet);
        detailListenNumTv.setText(songSheetInfo.listenum);
        detailSongSheetTv.setText(songSheetInfo.title);
        detailSongSheetTagTv.setText(songSheetInfo.tag);
        detailCollectTv.setText(songSheetInfo.collectnum);
    }

    @Override
    public void loadSongList(List<ContentBean> contentBeans) {
        if (mPresenter != null && mAdapter != null) {
            mPresenter.updateSongSheet(mAdapter, contentBeans);
        }
    }

    @Override
    public void setToolBarMarginTop() {
        CollapsingToolbarLayout.LayoutParams params = (CollapsingToolbarLayout.LayoutParams) tlDetail.getLayoutParams();
        params.setMargins(0, ScreenUtils.getStatusBarHeight(getActivity()), 0, 0);
    }

    @Override
    public void setToolBarBgAlpha(int alpha) {
        //保证只改变此处
        tlDetail.getBackground().mutate().setAlpha(alpha);
    }

    @Override
    public void setToolBarTitle(String title) {
        detailSongSheetTitleTv.setText(title);
    }

    @Override
    public void setToolBarSubTitle(String subTitle) {
        detailSongSheetDescTv.setText(subTitle);
    }

    @Override
    public void setInfoAlpha(float alpha) {
        llDetailInfo.setAlpha(alpha);
    }

    @Override
    public void setOperationAlpha(float alpha) {
        llDetailOperation.setAlpha(alpha);
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
        if (count == 0) {
            header.setVisibility(View.GONE);
        } else {
            header.setVisibility(View.VISIBLE);
            headerHolder.songCountTv.setText(getString(R.string.detail_song_count, String.valueOf(count)));
        }
    }

    @Override
    public String getPageName() {
        return TAG;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @OnClick({R.id.back_iv, R.id.more_iv, R.id.search_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_iv:
                if (mPresenter != null) {
                    mPresenter.onBack();
                }
                break;
            case R.id.more_iv:
                break;
            case R.id.search_iv:
                if (mPresenter != null) {
                    mPresenter.doSearch();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        if (mPresenter != null) {
            mPresenter.setToolBarStatus(verticalOffset, maxScroll);
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
