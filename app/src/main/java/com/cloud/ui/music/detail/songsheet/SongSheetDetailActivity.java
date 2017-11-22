package com.cloud.ui.music.detail.songsheet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.cloud.R;
import com.cloud.ui.BaseActivity;
import com.magical.library.utils.StatusBarUtils;

/**
 * Project: CloudStation
 * FileName: SongSheetDetailActivity.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/23/17 3:55 PM
 * Editor: ldy
 * Modify Date: 8/23/17 3:55 PM
 * Remark:
 */
public class SongSheetDetailActivity extends BaseActivity {

    private static final String TAG = SongSheetDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setStatusBarFromImg(this, ContextCompat.getColor(this, R.color.transparent));
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        String id = getIntent().getStringExtra("listid");
        if (TextUtils.isEmpty(id)) {
            finish();
            return;
        }
        SongSheetDetailFragment fragment = new SongSheetDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("listid", id);
        fragment.setArguments(bundle);
        addFragment(fragment);
    }

    @Override
    protected int fragmentRoot() {
        return R.id.root;
    }

    @Override
    protected String getPageName() {
        return TAG;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_music_detail;
    }
}
