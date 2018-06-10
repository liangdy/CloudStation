package com.cloud.ui.music.bottom;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cloud.R;
import com.cloud.ui.BaseFragment;
import com.magical.library.view.XImageView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Project: CloudStation
 * FileName: BottomControlFragment.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 12/2/17 4:03 PM
 * Editor: ldy
 * Modify Date: 12/2/17 4:03 PM
 * Remark:
 */
public class BottomControlFragment extends BaseFragment implements BottomControlContract.View {

    private static final String TAG = BottomControlFragment.class.getSimpleName();
    @BindView(R.id.song_cover_iv)
    XImageView songCoverIv;
    @BindView(R.id.show_list_iv)
    ImageView showListIv;
    @BindView(R.id.play_iv)
    ImageView playIv;
    @BindView(R.id.next_iv)
    ImageView nextIv;
    @BindView(R.id.song_name_tv)
    TextView songNameTv;
    @BindView(R.id.song_lrc_tv)
    TextView songLrcTv;
    @BindView(R.id.bottom_control_pb)
    ProgressBar bottomControlPb;
    @BindView(R.id.bottom_control_layout)
    LinearLayout bottomControlLayout;

    @Inject
    BottomControlPresenter mPresenter;


    @Override
    protected void initInjector() {
        DaggerBottomControlComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .bottomControlModule(new BottomControlModule())
                .build()
                .inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_bottom_control;
    }

    @Override
    public void initData() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void initialized() {

    }

    @OnClick({R.id.show_list_iv, R.id.play_iv, R.id.next_iv, R.id.bottom_control_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.show_list_iv:
                break;
            case R.id.play_iv:
                break;
            case R.id.next_iv:
                break;
            case R.id.bottom_control_layout:
                break;
        }
    }

    @Override
    public String getPageName() {
        return TAG;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
