package com.cloud.ui.music.manager;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cloud.R;
import com.cloud.ui.BaseFragment;
import com.cloud.utils.TextParser;
import com.magical.library.utils.UiUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Project: CloudStation
 * FileName: ManagerFragment.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/3/17 3:36 PM
 * Editor: ldy
 * Modify Date: 3/3/17 3:36 PM
 * Remark:
 */
public class ManagerFragment extends BaseFragment implements ManagerContract.View {

    private static final String TAG = ManagerFragment.class.getSimpleName();
    @BindView(R.id.local_music_layout)
    LinearLayout localMusicLayout;
    @BindView(R.id.recent_play_layout)
    LinearLayout recentPlayLayout;
    @BindView(R.id.download_manager_layout)
    LinearLayout downloadManagerLayout;
    @BindView(R.id.my_singer_layout)
    LinearLayout mySingerLayout;
    @BindView(R.id.local_music_tv)
    TextView localMusicTv;
    @BindView(R.id.recent_play_tv)
    TextView recentPlayTv;
    @BindView(R.id.download_manager_tv)
    TextView downloadManagerTv;
    @BindView(R.id.my_singer_tv)
    TextView mySingerTv;

    @Inject
    ManagerPresenter mPresenter;

    @Override
    protected void initInjector() {
        DaggerManagerComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .managerModule(new ManagerModule())
                .build()
                .inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_manager;
    }

    @Override
    public void initData() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public String getPageName() {
        return TAG;
    }

    @OnClick({R.id.local_music_layout, R.id.recent_play_layout, R.id.download_manager_layout, R.id.my_singer_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.local_music_layout:
                if (mPresenter != null) {
                    mPresenter.goLocalMusic(0);
                }
                break;
            case R.id.recent_play_layout:
                if (mPresenter != null) {
                    mPresenter.goRecentPlay();
                }
                break;
            case R.id.download_manager_layout:
                if (mPresenter != null) {
                    mPresenter.goDownloadManager();
                }
                break;
            case R.id.my_singer_layout:
                if (mPresenter != null) {
                    mPresenter.goLocalMusic(1);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void showLocalMusic(String count) {
        String info = getString(R.string.local_music) + "（" + count + "）";
        TextParser parser = new TextParser();
        parser.append("（" + count + "）", UiUtils.dp2px(getActivity(), 12), R.color.colorLightGray);
        parser.parse(localMusicTv, info);
        localMusicTv.setMovementMethod(null);
    }

    @Override
    public void showRecentPlay(String count) {
        String info = getString(R.string.recent_play) + "（" + count + "）";
        TextParser parser = new TextParser();
        parser.append("（" + count + "）", UiUtils.dp2px(getActivity(), 12), R.color.colorLightGray);
        parser.parse(recentPlayTv, info);
        recentPlayTv.setMovementMethod(null);
    }

    @Override
    public void showDownload(String count) {
        String info = getString(R.string.download_manager) + "（" + count + "）";
        TextParser parser = new TextParser();
        parser.append("（" + count + "）", UiUtils.dp2px(getActivity(), 12), R.color.colorLightGray);
        parser.parse(downloadManagerTv, info);
        downloadManagerTv.setMovementMethod(null);
    }

    @Override
    public void showMySinger(String count) {
        String info = getString(R.string.my_singer) + "（" + count + "）";
        TextParser parser = new TextParser();
        parser.append("（" + count + "）", UiUtils.dp2px(getActivity(), 12), R.color.colorLightGray);
        parser.parse(mySingerTv, info);
        mySingerTv.setMovementMethod(null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
