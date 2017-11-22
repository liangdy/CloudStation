package com.cloud.ui.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cloud.R;
import com.cloud.model.music.Album;
import com.cloud.model.music.Music;
import com.cloud.model.music.RecdList;
import com.cloud.model.music.SongSheet;
import com.cloud.ui.BaseActivity;
import com.cloud.view.XGridView;
import com.magical.library.view.recycler.AdapterItem;
import com.magical.library.view.recycler.SimpleItem;
import com.magical.library.view.recycler.dapter.CommonRcvAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Project: CloudStation
 * FileName: RecdSongAdapter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/15/17 2:55 PM
 * Editor: ldy
 * Modify Date: 3/15/17 2:55 PM
 * Remark:
 */
public class RecommendAdapter extends CommonRcvAdapter<RecdList> {

    private BaseActivity mContext;

    private ActionListener mListener;

    public void setListener(ActionListener mListener) {
        this.mListener = mListener;
    }

    @Inject
    protected RecommendAdapter(BaseActivity mContext, List<RecdList> data) {
        super(data);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public AdapterItem<RecdList> getItemView(Object type) {
        int cType = Integer.valueOf(type.toString());
        if (cType == RecdList.TYPE_HOT_SONG_SHEET) {
            return new HotSongSheetItem();
        } else if (cType == RecdList.TYPE_RECD_ALBUM) {
            return new RecdAlbumListItem();
        } else if (cType == RecdList.TYPE_RECD_MUSIC) {
            return new RecdMusicListItem();
        }
        return null;
    }

    @Override
    public Object getItemViewType(RecdList recdList) {
        return recdList.type;
    }

    class HotSongSheetItem extends SimpleItem<RecdList> {

        @BindView(R.id.item_title)
        TextView itemTitle;
        @BindView(R.id.item_more)
        TextView itemMore;
        @BindView(R.id.recd_grid)
        XGridView recdGrid;

        @Override
        public int getLayoutResId() {
            return R.layout.item_grid;
        }

        @Override
        public void onUpdateViews(RecdList model, int position) {
            if (model == null) {
                return;
            }
            SongSheet songSheet = (SongSheet) model.object;
            if (songSheet == null) {
                return;
            }

            final List<SongSheet.SongSheetBean> datas = songSheet.content.list;

            if (datas == null || datas.isEmpty()) {
                return;
            }
            itemMore.setVisibility(View.VISIBLE);
            itemTitle.setText(R.string.music_hot_song_sheet);
            Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.icon_recd_song_list);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            itemTitle.setCompoundDrawables(drawable, null, null, null);
            recdGrid.setAdapter(new HotSongGridAdapter(mContext, R.layout.item_song_cover, datas));
            recdGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (mListener != null) {
                        mListener.clickHotSongSheet(datas.get(position));
                    }
                }
            });
        }

        @OnClick(R.id.item_more)
        public void onClick() {
            if (mListener != null) {
                mListener.clickMore();
            }
        }
    }

    class RecdAlbumListItem extends SimpleItem<RecdList> {

        @BindView(R.id.item_title)
        TextView itemTitle;
        @BindView(R.id.item_more)
        TextView itemMore;
        @BindView(R.id.recd_grid)
        XGridView recdGrid;
        @BindView(R.id.item)
        LinearLayout item;

        @Override
        public int getLayoutResId() {
            return R.layout.item_grid;
        }

        @Override
        public void onUpdateViews(RecdList model, int position) {
            if (model == null) {
                return;
            }
            Album album = (Album) model.object;
            if (album == null) {
                return;
            }
            final List<Album.ListBean> datas = album.plaze_album_list.RM.album_list.list;
            if (datas == null || datas.isEmpty()) {
                return;
            }
            itemMore.setVisibility(View.GONE);
            itemTitle.setText(R.string.music_recd_album);
            Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.icon_recd_new_list);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            itemTitle.setCompoundDrawables(drawable, null, null, null);
            recdGrid.setAdapter(new RecdAlbumGridAdapter(mContext, R.layout.item_song_cover, datas));
            recdGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (mListener != null) {
                        mListener.clickRecdAlbum(datas.get(position));
                    }
                }
            });
        }
    }

    class RecdMusicListItem extends SimpleItem<RecdList> {

        @BindView(R.id.item_title)
        TextView itemTitle;
        @BindView(R.id.item_more)
        TextView itemMore;
        @BindView(R.id.recd_grid)
        XGridView recdGrid;

        @Override
        public int getLayoutResId() {
            return R.layout.item_list;
        }

        @Override
        public void onUpdateViews(RecdList model, int position) {
            if (model == null) {
                return;
            }
            Music music = (Music) model.object;
            if (music == null) {
                return;
            }
            List<Music.ContentBean> contents = music.content;
            if (contents == null || contents.isEmpty()) {
                return;
            }
            final List<Music.SongListBean> datas = contents.get(0).song_list;
            if (datas == null || datas.isEmpty()) {
                return;
            }
            itemMore.setVisibility(View.VISIBLE);
            itemTitle.setText(R.string.music_day_recd_song);
            Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.icon_recd_day_list);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            itemTitle.setCompoundDrawables(drawable, null, null, null);
            recdGrid.setAdapter(new DayRecdSongGridAdapter(mContext, R.layout.item_music, datas));
            recdGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (mListener != null) {
                        mListener.clickDailyRecdMusic(datas.get(position));
                    }
                }
            });
        }
    }

    public interface ActionListener {
        void clickHotSongSheet(SongSheet.SongSheetBean songSheetBean);

        void clickRecdAlbum(Album.ListBean albumBean);

        void clickDailyRecdMusic(Music.SongListBean songListBean);

        void clickMore();
    }
}
