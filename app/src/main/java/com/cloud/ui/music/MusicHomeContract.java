package com.cloud.ui.music;

import com.cloud.ui.BasePresenter;
import com.cloud.ui.BaseView;

/**
 * Project: CloudStation
 * FileName: MusicHomeContract.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/3/17 10:26 AM
 * Editor: ldy
 * Modify Date: 3/3/17 10:26 AM
 * Remark:
 */
public interface MusicHomeContract {

    interface View extends BaseView {
        void initialized();
    }

    interface Presenter extends BasePresenter<MusicHomeContract.View> {

    }
}
