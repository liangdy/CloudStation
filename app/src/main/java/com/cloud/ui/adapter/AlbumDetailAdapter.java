package com.cloud.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cloud.R;
import com.cloud.model.music.AlbumDetail;
import com.cloud.ui.BaseActivity;
import com.magical.library.view.recycler.AdapterItem;
import com.magical.library.view.recycler.SimpleItem;
import com.magical.library.view.recycler.dapter.CommonRcvAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Project: CloudStation
 * FileName: SongSheetDetailAdapter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/28/17 5:47 PM
 * Editor: ldy
 * Modify Date: 8/28/17 5:47 PM
 * Remark:
 */
public class AlbumDetailAdapter extends CommonRcvAdapter<AlbumDetail.SongListBean> {

    private ActionListener mListener;

    private BaseActivity mActivity;

    public void setListener(ActionListener listener) {
        this.mListener = listener;
    }

    @Inject
    protected AlbumDetailAdapter(List<AlbumDetail.SongListBean> data, BaseActivity activity) {
        super(data);
        mActivity = activity;
    }

    @NonNull
    @Override
    public AdapterItem<AlbumDetail.SongListBean> getItemView(Object type) {
        return new SongItem();
    }

    class SongItem extends SimpleItem<AlbumDetail.SongListBean> {

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

        private AlbumDetail.SongListBean bean;

        @Override
        public int getLayoutResId() {
            return R.layout.item_album_detail;
        }

        @Override
        public void onUpdateViews(AlbumDetail.SongListBean model, int position) {
            if (model == null) {
                return;
            }
            bean = model;
            listCountTv.setText(String.valueOf(position + 1));
            musicNameTv.setText(model.title);
            authorTv.setText(model.author);
        }

        @OnClick({R.id.more_iv, R.id.item})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.more_iv:
                    if (mListener != null) {
                        mListener.actionMore(bean);
                    }
                    break;
                case R.id.item:
                    if (mListener != null) {
                        mListener.itemClick(bean);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public interface ActionListener {

        void actionMore(AlbumDetail.SongListBean bean);

        void itemClick(AlbumDetail.SongListBean bean);
    }
}
