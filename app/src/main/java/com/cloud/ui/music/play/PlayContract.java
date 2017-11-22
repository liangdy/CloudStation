package com.cloud.ui.music.play;

import com.cloud.ui.BasePresenter;
import com.cloud.ui.BaseView;

/**
 * Project: CloudStation
 * FileName: PlayConstract.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/3/17 3:34 PM
 * Editor: ldy
 * Modify Date: 9/3/17 3:34 PM
 * Remark:
 */
public interface PlayContract {

    interface View extends BaseView {

        void initialized();
    }

    interface Presenter extends BasePresenter<PlayContract.View> {

        void onBack();
    }
}
