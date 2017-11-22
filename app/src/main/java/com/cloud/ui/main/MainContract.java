package com.cloud.ui.main;

import com.cloud.ui.BasePresenter;
import com.cloud.ui.BaseView;

/**
 * Project: CloudStation
 * FileName: MainContract.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 2/23/17 10:40 AM
 * Editor: ldy
 * Modify Date: 2/23/17 10:40 AM
 * Remark:
 */
public interface MainContract {

    interface View extends BaseView {
        void initialized();

        void closeDrawer();

        void openDrawer();

        void setTitleBar(String title);
    }

    interface Presenter extends BasePresenter<MainContract.View> {

        void showNavContainer();

        void showMainContainer(int type);

        void exit(android.view.View rootView);
    }
}
