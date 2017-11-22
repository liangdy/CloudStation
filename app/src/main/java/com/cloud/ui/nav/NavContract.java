package com.cloud.ui.nav;

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
public interface NavContract {

    interface View extends BaseView {
        void initialized();
    }

    interface Presenter extends BasePresenter<NavContract.View> {
        void goMainPage(int type);
    }
}
