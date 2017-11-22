package com.cloud.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cloud.R;
import com.cloud.model.music.MusicInfo;
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
public class LocalSongAdapter extends CommonRcvAdapter<MusicInfo> {

    private ActionListener listener;

    public void setListener(ActionListener listener) {
        this.listener = listener;
    }

    @Inject
    protected LocalSongAdapter(List<MusicInfo> data) {
        super(data);
    }

    @NonNull
    @Override
    public AdapterItem<MusicInfo> getItemView(Object type) {
        return new LocalSongItem();
    }

    public class LocalSongItem extends SimpleItem<MusicInfo> {

        @BindView(R.id.list_count_tv)
        TextView listCountTv;
        @BindView(R.id.more_iv)
        ImageView moreIv;
        @BindView(R.id.music_name_tv)
        TextView musicNameTv;
        @BindView(R.id.author_tv)
        TextView authorTv;
        @BindView(R.id.item)
        RelativeLayout item;

        private MusicInfo mMusicInfo;

        @Override
        public int getLayoutResId() {
            return R.layout.item_local_song;
        }

        @Override
        public void onUpdateViews(MusicInfo model, int position) {
            if (model != null) {
                listCountTv.setText(String.valueOf(position + 1));
                musicNameTv.setText(model.albumName);
                authorTv.setText(model.artist);
                mMusicInfo = model;
            }
        }

        @OnClick({R.id.more_iv, R.id.item})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.more_iv:
                    if (listener != null) {
                        listener.actionMore(mMusicInfo);
                    }
                    break;
                case R.id.item:
                    if (listener != null) {
                        listener.onItemClick(mMusicInfo);
                    }
                    break;
            }
        }
    }

    public interface ActionListener {
        void actionMore(MusicInfo model);

        void onItemClick(MusicInfo model);
    }
}
