package com.cloud.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cloud.R;
import com.cloud.model.music.FolderInfo;
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
 * FileName: LocalSongAdapter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/4/17 11:01 AM
 * Editor: ldy
 * Modify Date: 9/4/17 11:01 AM
 * Remark:
 */
public class LocalFolderAdapter extends CommonRcvAdapter<FolderInfo> {

    private BaseActivity mActivity;
    private ActionListener listener;

    public void setListener(ActionListener listener) {
        this.listener = listener;
    }

    @Inject
    protected LocalFolderAdapter(List<FolderInfo> data, BaseActivity activity) {
        super(data);
        this.mActivity = activity;
    }

    @NonNull
    @Override
    public AdapterItem<FolderInfo> getItemView(Object type) {
        return new LocalFolderItem();
    }

    public class LocalFolderItem extends SimpleItem<FolderInfo> {

        @BindView(R.id.more_iv)
        ImageView moreIv;
        @BindView(R.id.folder_tv)
        TextView folderTv;
        @BindView(R.id.song_count_iv)
        TextView songCountIv;
        @BindView(R.id.item)
        RelativeLayout item;
        private FolderInfo mFolderInfo;

        @Override
        public int getLayoutResId() {
            return R.layout.item_local_folder;
        }

        @Override
        public void onUpdateViews(FolderInfo model, int position) {
            if (model != null) {
                mFolderInfo = model;
                folderTv.setText(model.folder_name);
                songCountIv.setText(mActivity.getString(R.string.detail_song_count1, String.valueOf(model.folder_count)) + " " + model.folder_path);
            }
        }

        @OnClick({R.id.more_iv, R.id.item})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.more_iv:
                    if (listener != null) {
                        listener.actionMore(mFolderInfo);
                    }
                    break;
                case R.id.item:
                    if (listener != null) {
                        listener.onItemClick(mFolderInfo);
                    }
                    break;
            }
        }
    }

    public interface ActionListener {
        void actionMore(FolderInfo model);

        void onItemClick(FolderInfo model);
    }
}
