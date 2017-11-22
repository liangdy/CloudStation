package com.cloud.ui.music.detail.ranking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.cloud.R;
import com.cloud.ui.BaseActivity;
import com.magical.library.utils.StatusBarUtils;

/**
 * Project: CloudStation
 * FileName: RankingDetailActivity.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/23/17 3:55 PM
 * Editor: ldy
 * Modify Date: 8/23/17 3:55 PM
 * Remark:
 */
public class RankingDetailActivity extends BaseActivity {

    private static final String TAG = RankingDetailActivity.class.getSimpleName();

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
        String type = getIntent().getStringExtra("type");
        if (TextUtils.isEmpty(type)) {
            finish();
            return;
        }
        RankingDetailFragment fragment = new RankingDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
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
