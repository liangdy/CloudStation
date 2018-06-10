package com.cloud.ui.music.bottom;

import com.cloud.ui.BasePresenter;
import com.cloud.ui.BaseView;

/**
 * Project: CloudStation
 * FileName: BottomControlContract.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 12/2/17 4:08 PM
 * Editor: ldy
 * Modify Date: 12/2/17 4:08 PM
 * Remark:
 */
public interface BottomControlContract {

    interface View extends BaseView {

        void initialized();
    }

    interface Presenter extends BasePresenter<BottomControlContract.View> {
        
    }
}
