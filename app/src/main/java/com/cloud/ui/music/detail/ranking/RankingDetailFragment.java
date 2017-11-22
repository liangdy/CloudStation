package com.cloud.ui.music.detail.ranking;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
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
import com.cloud.model.music.RankDetail;
import com.cloud.ui.BaseFragment;
import com.cloud.ui.adapter.RankingDetailAdapter;
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
 * FileName: RankingDetailFragment.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/1/17 6:30 PM
 * Editor: ldy
 * Modify Date: 9/1/17 6:30 PM
 * Remark:
 */
public class RankingDetailFragment extends BaseFragment implements RankingDetailContract.View, AppBarLayout.OnOffsetChangedListener {

    public static final String TAG = RankingDetailFragment.class.getSimpleName();

    @Inject
    RankingDetailPresenter mPresenter;
    @BindView(R.id.detail_bg)
    XImageView detailBg;
    @BindView(R.id.back_iv)
    ImageView backIv;
    @BindView(R.id.more_iv)
    ImageView moreIv;
    @BindView(R.id.search_iv)
    ImageView searchIv;
    @BindView(R.id.detail_ranking_title_tv)
    TextView detailRankingTitleTv;
    @BindView(R.id.detail_ranking_desc_tv)
    TextView detailRankingDescTv;
    @BindView(R.id.tl_detail)
    Toolbar tlDetail;
    @BindView(R.id.detail_ranking_iv)
    XImageView detailRankingIv;
    @BindView(R.id.detail_listen_num_tv)
    TextView detailListenNumTv;
    @BindView(R.id.ll_detail_listen_num)
    LinearLayout llDetailListenNum;
    @BindView(R.id.rl_detail_ranking)
    RelativeLayout rlDetailRanking;
    @BindView(R.id.detail_ranking_tv)
    TextView detailRankingTv;
    @BindView(R.id.detail_ranking_tag_tv)
    TextView detailRankingTagTv;
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
    RankingDetailAdapter mAdapter;

    private View header;
    private ViewHolder headerHolder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initInjector() {
        DaggerRankingDetailComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .rankingDetailModule(new RankingDetailModule())
                .build()
                .inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_ranking_detail;
    }

    @Override
    public void initData() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
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
        mAdapter.setListener(new RankingDetailAdapter.ActionListener() {
            @Override
            public void actionMore(ContentBean bean) {

            }

            @Override
            public void itemClick(ContentBean bean) {

            }
        });
    }

    @Override
    public void loadRankingDetail() {
        String type = getArguments().getString("type");
        if (mPresenter != null && !TextUtils.isEmpty(type)) {
            mPresenter.getRankingDetail(type);
        }
    }

    @Override
    public void loadSongList(List<ContentBean> contentBeans) {
        if (mPresenter != null && mAdapter != null) {
            mPresenter.updateRanking(mAdapter, contentBeans);
        }
    }

    @Override
    public void setRankingInfo(RankDetail.BillboardBean rankingInfo) {
        ImageLoader.loadBlurImage(detailBg, rankingInfo.pic_s260, EncryptUtils.getMD5(rankingInfo.pic_s260), 60f);
        ImageLoader.loadImage(detailRankingIv, rankingInfo.pic_s260, EncryptUtils.getMD5(rankingInfo.pic_s260));
        detailRankingTitleTv.setText(R.string.detail_ranking);
        detailRankingTv.setText(rankingInfo.name);
        detailRankingTagTv.setText(rankingInfo.comment);
        detailCollectTv.setText(rankingInfo.billboard_no);
    }

    @Override
    public void setToolBarMarginTop() {
        CollapsingToolbarLayout.LayoutParams params = (CollapsingToolbarLayout.LayoutParams) tlDetail.getLayoutParams();
        params.setMargins(0, ScreenUtils.getStatusBarHeight(getActivity()), 0, 0);
    }

    @Override
    public void setToolBarBgAlpha(int alpha) {
        tlDetail.getBackground().mutate().setAlpha(alpha);
    }

    @Override
    public void setToolBarTitle(String title) {
        detailRankingTitleTv.setText(title);
    }

    @Override
    public void setToolBarSubTitle(String subTitle) {
        detailRankingDescTv.setText(subTitle);
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
    public void onDetach() {
        super.onDetach();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        if (mPresenter != null) {
            mPresenter.setToolBarStatus(verticalOffset, maxScroll);
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
