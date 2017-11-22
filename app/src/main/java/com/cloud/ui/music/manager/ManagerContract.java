package com.cloud.ui.music.manager;

import com.cloud.ui.BasePresenter;
import com.cloud.ui.BaseView;

/**
 * Project: CloudStation
 * FileName: ManagerConstract.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/14/17 4:27 PM
 * Editor: ldy
 * Modify Date: 8/14/17 4:27 PM
 * Remark:
 */
public interface ManagerContract {

    interface View extends BaseView {

        void showLocalMusic(String count);

        void showRecentPlay(String count);

        void showDownload(String count);

        void showMySinger(String count);
    }

    interface Presenter extends BasePresenter<ManagerContract.View> {

        void loadCount();

        void goLocalMusic(int tab);

        void goRecentPlay();

        void goDownloadManager();
    }
}
