package com.cloud.ui.music.detail.album;

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
import com.cloud.model.music.AlbumDetail;
import com.cloud.model.music.AlbumInfo;
import com.cloud.ui.BaseFragment;
import com.cloud.ui.adapter.AlbumDetailAdapter;
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
 * FileName: AlbumDetailFragment.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/29/17 4:38 PM
 * Editor: ldy
 * Modify Date: 8/29/17 4:38 PM
 * Remark:
 */
public class AlbumDetailFragment extends BaseFragment implements AlbumDetailContract.View, AppBarLayout.OnOffsetChangedListener {

    private static final String TAG = AlbumDetailFragment.class.getSimpleName();

    @Inject
    AlbumDetailPresenter mPresenter;
    @BindView(R.id.detail_bg)
    XImageView detailBg;
    @BindView(R.id.back_iv)
    ImageView backIv;
    @BindView(R.id.more_iv)
    ImageView moreIv;
    @BindView(R.id.search_iv)
    ImageView searchIv;
    @BindView(R.id.detail_album_title_tv)
    TextView detailAlbumTitleTv;
    @BindView(R.id.detail_album_desc_tv)
    TextView detailAlbumDescTv;
    @BindView(R.id.tl_detail)
    Toolbar tlDetail;
    @BindView(R.id.detail_album_iv)
    XImageView detailAlbumIv;
    @BindView(R.id.detail_listen_num_tv)
    TextView detailListenNumTv;
    @BindView(R.id.ll_detail_listen_num)
    LinearLayout llDetailListenNum;
    @BindView(R.id.rl_detail_album)
    RelativeLayout rlDetailAlbum;
    @BindView(R.id.detail_album_tv)
    TextView detailAlbumTv;
    @BindView(R.id.detail_album_tag_tv)
    TextView detailAlbumTagTv;
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
    AlbumDetailAdapter mAdapter;

    private View header;
    private ViewHolder headerHolder;

    @Override
    protected void initInjector() {
        DaggerAlbumDetailComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .albumDetailModule(new AlbumDetailModule())
                .build()
                .inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_album_detail;
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
        mAdapter.setListener(new AlbumDetailAdapter.ActionListener() {
            @Override
            public void actionMore(AlbumDetail.SongListBean bean) {

            }

            @Override
            public void itemClick(AlbumDetail.SongListBean bean) {

            }
        });
    }

    @Override
    public void loadAlbumDetail() {
        String albumId = getArguments().getString("album_id");
        if (mPresenter != null && !TextUtils.isEmpty(albumId)) {
            mPresenter.getAlbumDetail(albumId);
        }
    }

    @Override
    public void setAlbumInfo(AlbumInfo albumInfo) {
        ImageLoader.loadBlurImage(detailBg, albumInfo.pic_s500, EncryptUtils.getMD5(albumInfo.pic_s500), 60f);
        ImageLoader.loadImage(detailAlbumIv, albumInfo.pic_s500, EncryptUtils.getMD5(albumInfo.pic_s500));
        detailAlbumTitleTv.setText(R.string.detail_album);
        detailListenNumTv.setText(String.valueOf(albumInfo.listen_num));
        detailAlbumTv.setText(albumInfo.title);
        detailAlbumDescTv.setText(albumInfo.author);
        detailAlbumTagTv.setText(albumInfo.info);
        detailCollectTv.setText(String.valueOf(albumInfo.collect_num));
        detailCommentTv.setText(String.valueOf(albumInfo.comment_num));
        detailShareTv.setText(String.valueOf(albumInfo.share_num));
    }

    @Override
    public void loadSongList(List<AlbumDetail.SongListBean> songListBeans) {
        if (mPresenter != null && mAdapter != null) {
            mPresenter.updateAlbum(mAdapter, songListBeans);
        }
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
        detailAlbumTitleTv.setText(title);
    }

    @Override
    public void setToolBarSubTitle(String subTitle) {
        detailAlbumDescTv.setText(subTitle);
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
