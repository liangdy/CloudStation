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
import android.widget.TextView;

import com.cloud.R;
import com.cloud.model.music.Music;
import com.magical.library.imageloader.ImageLoader;
import com.magical.library.utils.EncryptUtils;
import com.magical.library.view.XImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Project: CloudStation
 * FileName: OriginalSongGridAdapter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/20/17 10:48 AM
 * Editor: ldy
 * Modify Date: 3/20/17 10:48 AM
 * Remark:
 */
public class DayRecdSongGridAdapter extends ArrayAdapter<Music.SongListBean> {

    private Activity mContext;

    public DayRecdSongGridAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Music.SongListBean> objects) {
        super(context, resource, objects);
        mContext = (Activity) context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Music.SongListBean recdMusic = getItem(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = mContext.getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_music, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (recdMusic != null) {
            ImageLoader.loadImage(viewHolder.musicCoverIv, recdMusic.pic_premium, EncryptUtils.getMD5(recdMusic.pic_premium));
            viewHolder.musicNameTv.setText(recdMusic.title);
            viewHolder.authorTv.setText(recdMusic.author);
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.music_cover_iv)
        XImageView musicCoverIv;
        @BindView(R.id.music_name_tv)
        TextView musicNameTv;
        @BindView(R.id.author_tv)
        TextView authorTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
