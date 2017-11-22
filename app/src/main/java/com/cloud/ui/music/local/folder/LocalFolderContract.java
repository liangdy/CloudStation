package com.cloud.ui.music.local.folder;

import com.cloud.model.music.FolderInfo;
import com.cloud.ui.BasePresenter;
import com.cloud.ui.BaseView;
import com.cloud.ui.adapter.LocalFolderAdapter;

import java.util.List;

/**
 * Project: CloudStation
 * FileName: LocalFolderContract.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/6/17 2:45 PM
 * Editor: ldy
 * Modify Date: 9/6/17 2:45 PM
 * Remark:
 */
public interface LocalFolderContract {

    interface View extends BaseView {

        void initialized();

        void loadLocalFolder();

        void updateFolderInfo(List<FolderInfo> folderInfos);
    }

    interface Presenter extends BasePresenter<LocalFolderContract.View> {

        void getLocalFolder();

        void updateFolderInfo(LocalFolderAdapter adapter, List<FolderInfo> folderInfos);
    }
}
