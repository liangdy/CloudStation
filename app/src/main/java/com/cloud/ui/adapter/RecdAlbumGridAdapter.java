package com.cloud.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cloud.R;
import com.cloud.model.music.Album;
import com.magical.library.imageloader.ImageLoader;
import com.magical.library.utils.EncryptUtils;
import com.magical.library.utils.ScreenUtils;
import com.magical.library.utils.UiUtils;
import com.magical.library.view.XImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Project: CloudStation
 * FileName: SongGridAdapter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/15/17 5:03 PM
 * Editor: ldy
 * Modify Date: 3/15/17 5:03 PM
 * Remark:
 */
public class RecdAlbumGridAdapter extends ArrayAdapter<Album.ListBean> {

    private Activity mContext;

    public RecdAlbumGridAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Album.ListBean> objects) {
        super(context, resource, objects);
        mContext = (Activity) context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Album.ListBean listBean = getItem(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = mContext.getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_song_cover, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (listBean != null) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewHolder.coverLayout.getLayoutParams();
            params.width = (ScreenUtils.getScreenWidth(mContext) - UiUtils.dp2px(mContext, 36)) / 3;
            params.height = params.width;
            viewHolder.coverLayout.setLayoutParams(params);
            ImageLoader.loadImage(viewHolder.imgCover, listBean.pic_big, EncryptUtils.getMD5(listBean.pic_big));
            viewHolder.listenNumTv.setVisibility(View.GONE);
            viewHolder.desc.setText(listBean.title);
            viewHolder.author.setVisibility(View.GONE);
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.img_cover)
        XImageView imgCover;
        @BindView(R.id.listen_num_tv)
        TextView listenNumTv;
        @BindView(R.id.desc)
        TextView desc;
        @BindView(R.id.author)
        TextView author;
        @BindView(R.id.item)
        LinearLayout item;
        @BindView(R.id.cover_layout)
        FrameLayout coverLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
