package com.cloud.ui.music.local;

import com.cloud.ui.BasePresenter;
import com.cloud.ui.BaseView;

/**
 * Project: CloudStation
 * FileName: LocalMusicContract.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/3/17 4:35 PM
 * Editor: ldy
 * Modify Date: 9/3/17 4:35 PM
 * Remark:
 */
public interface LocalMusicContract {
    
    interface View extends BaseView {

        void initialized();
    }

    interface Presenter extends BasePresenter<LocalMusicContract.View> {

        void onBack();
    }
}
