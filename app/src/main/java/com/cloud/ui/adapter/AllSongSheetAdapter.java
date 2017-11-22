package com.cloud.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cloud.R;
import com.cloud.model.music.AllSongSheet;
import com.cloud.ui.BaseActivity;
import com.magical.library.imageloader.ImageLoader;
import com.magical.library.utils.EncryptUtils;
import com.magical.library.utils.ScreenUtils;
import com.magical.library.utils.UiUtils;
import com.magical.library.view.XImageView;
import com.magical.library.view.recycler.AdapterItem;
import com.magical.library.view.recycler.dapter.CommonRcvAdapter;
import com.magical.library.view.recycler.SimpleItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Project: CloudStation
 * FileName: SongListAdapter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/21/17 7:20 PM
 * Editor: ldy
 * Modify Date: 3/21/17 7:20 PM
 * Remark:
 */
public class AllSongSheetAdapter extends CommonRcvAdapter<AllSongSheet.ContentBean> {

    private BaseActivity mActivity;

    @Inject
    public AllSongSheetAdapter(List<AllSongSheet.ContentBean> data, BaseActivity mActivity) {
        super(data);
        this.mActivity = mActivity;
    }

    @NonNull
    @Override
    public AdapterItem<AllSongSheet.ContentBean> getItemView(Object type) {
        return new AllSongSheetItem();
    }

    class AllSongSheetItem extends SimpleItem<AllSongSheet.ContentBean> {

        @BindView(R.id.img_cover)
        XImageView imgCover;
        @BindView(R.id.cover_iv)
        View coverIv;
        @BindView(R.id.listen_num_tv)
        TextView listenNumTv;
        @BindView(R.id.cover_layout)
        FrameLayout coverLayout;
        @BindView(R.id.desc)
        TextView desc;
        @BindView(R.id.author)
        TextView author;
        @BindView(R.id.item)
        LinearLayout item;

        @Override
        public int getLayoutResId() {
            return R.layout.item_song_cover;
        }

        @Override
        public void onUpdateViews(AllSongSheet.ContentBean model, int position) {
            if (model == null) {
                return;
            }
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) coverLayout.getLayoutParams();
            params.width = (ScreenUtils.getScreenWidth(mActivity) - UiUtils.dp2px(mActivity, 30)) / 2;
            params.height = params.width;
            coverLayout.setLayoutParams(params);
            ImageLoader.loadImage(imgCover, model.pic_300, EncryptUtils.getMD5(model.pic_300));
            listenNumTv.setText(model.listenum + "");
            desc.setText(model.title);
            author.setVisibility(View.GONE);
        }
    }
}
