package com.cloud.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cloud.model.music.ArtistInfo;
import com.cloud.R;
import com.cloud.ui.BaseActivity;
import com.magical.library.view.XImageView;
import com.magical.library.view.recycler.AdapterItem;
import com.magical.library.view.recycler.SimpleItem;
import com.magical.library.view.recycler.dapter.CommonRcvAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Project: CloudStation
 * FileName: LocalSongAdapter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/4/17 11:01 AM
 * Editor: ldy
 * Modify Date: 9/4/17 11:01 AM
 * Remark:
 */
public class LocalArtistAdapter extends CommonRcvAdapter<ArtistInfo> {

    private BaseActivity mActivity;
    private ActionListener listener;

    public void setListener(ActionListener listener) {
        this.listener = listener;
    }

    @Inject
    protected LocalArtistAdapter(List<ArtistInfo> data, BaseActivity activity) {
        super(data);
        this.mActivity = activity;
    }

    @NonNull
    @Override
    public AdapterItem<ArtistInfo> getItemView(Object type) {
        return new LocalArtistItem();
    }

    public class LocalArtistItem extends SimpleItem<ArtistInfo> {

        @BindView(R.id.author_iv)
        XImageView authorIv;
        @BindView(R.id.more_iv)
        ImageView moreIv;
        @BindView(R.id.author_tv)
        TextView authorTv;
        @BindView(R.id.song_count_iv)
        TextView songCountIv;
        @BindView(R.id.item)
        RelativeLayout item;
        private ArtistInfo mArtistInfo;

        @Override
        public int getLayoutResId() {
            return R.layout.item_local_artist;
        }

        @Override
        public void onUpdateViews(ArtistInfo model, int position) {
            if (model != null) {
                mArtistInfo = model;
                authorTv.setText(model.artist_name);
                songCountIv.setText(mActivity.getString(R.string.detail_song_count1, String.valueOf(model.number_of_tracks)));
                if (listener != null) {
                    listener.loadImage(authorIv, model.artist_name);
                }
            }
        }

        @OnClick({R.id.more_iv, R.id.item})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.more_iv:
                    if (listener != null) {
                        listener.actionMore(mArtistInfo);
                    }
                    break;
                case R.id.item:
                    if (listener != null) {
                        listener.onItemClick(mArtistInfo);
                    }
                    break;
            }
        }
    }

    public interface ActionListener {
        void actionMore(ArtistInfo model);

        void onItemClick(ArtistInfo model);

        void loadImage(XImageView iv, String artist);
    }
}
