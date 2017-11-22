package com.cloud.ui.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cloud.R;
import com.cloud.model.music.AlbumInfo;
import com.cloud.ui.BaseActivity;
import com.magical.library.imageloader.ImageLoader;
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
public class LocalAlbumAdapter extends CommonRcvAdapter<AlbumInfo> {

    private BaseActivity mActivity;
    private ActionListener listener;

    public void setListener(ActionListener listener) {
        this.listener = listener;
    }

    @Inject
    protected LocalAlbumAdapter(List<AlbumInfo> data, BaseActivity activity) {
        super(data);
        this.mActivity = activity;
    }

    @NonNull
    @Override
    public AdapterItem<AlbumInfo> getItemView(Object type) {
        return new LocalAlbumItem();
    }

    public class LocalAlbumItem extends SimpleItem<AlbumInfo> {

        @BindView(R.id.album_iv)
        XImageView albumIv;
        @BindView(R.id.more_iv)
        ImageView moreIv;
        @BindView(R.id.album_tv)
        TextView albumTv;
        @BindView(R.id.song_count_iv)
        TextView songCountIv;
        @BindView(R.id.item)
        RelativeLayout item;
        private AlbumInfo mAlbumInfo;

        @Override
        public int getLayoutResId() {
            return R.layout.item_local_album;
        }

        @Override
        public void onUpdateViews(AlbumInfo model, int position) {
            if (model != null) {
                mAlbumInfo = model;
                albumTv.setText(model.album_name);
                songCountIv.setText(model.album_artist + "  " + mActivity.getString(R.string.detail_song_count1, String.valueOf(model.number_of_songs)));
                ImageLoader.loadImageByUri(albumIv, Uri.parse(model.album_art));
            }
        }

        @OnClick({R.id.more_iv, R.id.item})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.more_iv:
                    if (listener != null) {
                        listener.actionMore(mAlbumInfo);
                    }
                    break;
                case R.id.item:
                    if (listener != null) {
                        listener.onItemClick(mAlbumInfo);
                    }
                    break;
            }
        }
    }

    public interface ActionListener {
        void actionMore(AlbumInfo model);

        void onItemClick(AlbumInfo model);
    }
}
