package com.cloud.ui.main;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.cloud.R;
import com.cloud.model.NavModel;
import com.cloud.ui.BaseActivity;
import com.cloud.ui.music.MusicHomeFragment;
import com.cloud.ui.nav.NavFragment;
import com.magical.library.utils.AppManager;

import java.util.List;

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
public class MainPresenter implements MainContract.Presenter {

    private static long DOUBLE_CLICK_TIME = 0L;

    private MainContract.View mMainView;
    private BaseActivity mActivity;

    @Inject
    public MainPresenter(BaseActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void attachView(@NonNull MainContract.View view) {
        mMainView = view;
        mMainView.initialized();
    }

    @Override
    public void detachView() {
        mMainView = null;
    }

    @Override
    public void showNavContainer() {
        NavFragment fragment = new NavFragment();
        mActivity.addFragment(fragment, R.id.nav_container);
    }

    @Override
    public void showMainContainer(int type) {
        mMainView.closeDrawer();
        List<NavModel> navModels = NavModel.getLocalNavList(mActivity);
        for (NavModel navModel : navModels) {
            if (navModel.navType == type) {
                mMainView.setTitleBar(navModel.navName);
            }
        }
        if (type == NavModel.TYPE_MUSIC) {
            mActivity.replaceFragment(new MusicHomeFragment(), R.id.main_container);
        } else if (type == NavModel.TYPE_VIDEO) {

        } else if (type == NavModel.TYPE_PICTURE) {

        } else if (type == NavModel.TYPE_JOKE) {

        }
    }

    @Override
    public void exit(View rootView) {
        Snackbar snackbar = Snackbar.make(rootView, R.string.exit_confirm, Snackbar.LENGTH_SHORT)
                .setAction(mActivity.getString(R.string.exit), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppManager.getAppManager().exit(mActivity);
                    }
                });
        if ((System.currentTimeMillis() - DOUBLE_CLICK_TIME) > 2000) {
            DOUBLE_CLICK_TIME = System.currentTimeMillis();
            snackbar.show();
        }
    }
}
