package com.cloud.ui.start;

import com.cloud.ui.BasePresenter;
import com.cloud.ui.BaseView;
import com.magical.library.view.XImageView;

import java.io.File;

/**
 * Project: CloudStation
 * FileName: StartContract.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 2/22/17 4:55 PM
 * Editor: ldy
 * Modify Date: 2/22/17 4:55 PM
 * Remark:
 */
public interface StartContract {

    interface View extends BaseView {
        void loadImageView(File file);
    }

    interface Presenter extends BasePresenter<View> {
        void onStartClick();

        void showStartPage(File file, XImageView imageView);
    }
}
