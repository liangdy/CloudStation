package com.cloud.ui.adapter;

import android.support.annotation.NonNull;
import android.widget.TextView;

import com.cloud.R;
import com.cloud.model.music.RankBean;
import com.cloud.ui.BaseActivity;
import com.cloud.utils.TextParser;
import com.magical.library.imageloader.ImageLoader;
import com.magical.library.utils.EncryptUtils;
import com.magical.library.utils.UiUtils;
import com.magical.library.view.XImageView;
import com.magical.library.view.recycler.AdapterItem;
import com.magical.library.view.recycler.SimpleItem;
import com.magical.library.view.recycler.dapter.CommonRcvAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Project: CloudStation
 * FileName: RankingAdapter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/14/17 11:52 AM
 * Editor: ldy
 * Modify Date: 8/14/17 11:52 AM
 * Remark:
 */
public class RankingAdapter extends CommonRcvAdapter<RankBean.ContentBean> {

    private BaseActivity mActivity;

    @Inject
    public RankingAdapter(List<RankBean.ContentBean> data, BaseActivity activity) {
        super(data);
        mActivity = activity;
    }

    @NonNull
    @Override
    public AdapterItem<RankBean.ContentBean> getItemView(Object type) {
        return new RankingItem();
    }

    class RankingItem extends SimpleItem<RankBean.ContentBean> {

        @BindView(R.id.rank_iv)
        XImageView rankIv;
        @BindView(R.id.song_tv1)
        TextView songTv1;
        @BindView(R.id.song_tv2)
        TextView songTv2;
        @BindView(R.id.song_tv3)
        TextView songTv3;

        @Override
        public int getLayoutResId() {
            return R.layout.item_ranking;
        }

        @Override
        public void onUpdateViews(RankBean.ContentBean model, int position) {
            ImageLoader.loadImage(rankIv, model.pic_s260, EncryptUtils.getMD5(model.pic_s260));
            if (model.content.size() > 2) {
                String info1 = model.content.get(0).title + " - " + model.content.get(0).author;
                String info2 = model.content.get(1).title + " - " + model.content.get(1).author;
                String info3 = model.content.get(2).title + " - " + model.content.get(2).author;
                setText(songTv1, info1, model.content.get(0).author);
                setText(songTv2, info2, model.content.get(1).author);
                setText(songTv3, info3, model.content.get(2).author);
            } else if (model.content.size() > 1) {
                String info1 = model.content.get(0).title + " - " + model.content.get(0).author;
                String info2 = model.content.get(1).title + " - " + model.content.get(1).author;
                setText(songTv1, info1, model.content.get(0).author);
                setText(songTv2, info2, model.content.get(1).author);
                songTv3.setText("");
            } else if (model.content.size() > 0) {
                String info1 = model.content.get(0).title + " - " + model.content.get(0).author;
                setText(songTv1, info1, model.content.get(0).author);
                songTv2.setText("");
                songTv3.setText("");
            } else {
                songTv1.setText("");
                songTv2.setText("");
                songTv3.setText("");
            }
        }

        private void setText(TextView tv, String str1, String str2) {
            TextParser parser = new TextParser();
            parser.append(str2, UiUtils.dp2px(mActivity, 12), R.color.colorDarkGray);
            parser.parse(tv, str1);
            tv.setMovementMethod(null);
        }
    }
}
