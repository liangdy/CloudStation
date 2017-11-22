package com.cloud.ui.nav;

import android.support.annotation.NonNull;

import com.cloud.ui.BaseActivity;
import com.cloud.ui.main.MainActivity;

import javax.inject.Inject;

/**
 * Project: CloudStation
 * FileName: MainPresenter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 2/23/17 10:41 AM
 * Editor: ldy
 * Modify Date: 2/23/17 10:41 AM
 * Remark:
 */
public class NavPresenter implements NavContract.Presenter {

    private NavContract.View mMainView;
    private BaseActivity mActivity;

    @Inject
    public NavPresenter(BaseActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void attachView(@NonNull NavContract.View view) {
        mMainView = view;
        mMainView.initialized();
    }

    @Override
    public void detachView() {
        mMainView = null;
    }

    @Override
    public void goMainPage(int type) {
        if (mActivity instanceof MainActivity) {
            MainActivity activity = (MainActivity) mActivity;
            if (activity.mPresenter != null) {
                activity.mPresenter.showMainContainer(type);
            }
        }
    }
}
