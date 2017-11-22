package com.cloud.ui.music.local.folder;

import android.support.annotation.NonNull;

import com.cloud.model.music.FolderInfo;
import com.cloud.model.music.MusicInfo;
import com.cloud.ui.BaseActivity;
import com.cloud.ui.adapter.LocalFolderAdapter;
import com.cloud.ui.music.util.IConstants;
import com.cloud.ui.music.util.MusicManager;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Project: CloudStation
 * FileName: LocalFolderPresenter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/6/17 2:46 PM
 * Editor: ldy
 * Modify Date: 9/6/17 2:46 PM
 * Remark:
 */
public class LocalFolderPresenter implements LocalFolderContract.Presenter {

    private BaseActivity mActivity;

    private LocalFolderContract.View mLocalFolderView;

    private Disposable mDisposable;

    @Inject
    public LocalFolderPresenter(BaseActivity activity) {
        this.mActivity = activity;
    }

    @Override
    public void attachView(@NonNull LocalFolderContract.View view) {
        mLocalFolderView = view;
        mLocalFolderView.initialized();
        mLocalFolderView.loadLocalFolder();
    }

    @Override
    public void getLocalFolder() {
        Observable.fromArray(MusicManager.queryFolder(mActivity))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Disposable disposable) throws Exception {
                        mDisposable = disposable;
                    }
                }).filter(new Predicate<List<FolderInfo>>() {
            @Override
            public boolean test(@io.reactivex.annotations.NonNull List<FolderInfo> folderInfos) throws Exception {
                return (folderInfos != null && !folderInfos.isEmpty());
            }
        }).flatMap(new Function<List<FolderInfo>, ObservableSource<List<FolderInfo>>>() {
            @Override
            public ObservableSource<List<FolderInfo>> apply(@io.reactivex.annotations.NonNull List<FolderInfo> folderInfos) throws Exception {
                for (int i = 0; i < folderInfos.size(); i++) {
                    List<MusicInfo> albumList = MusicManager.queryMusic(mActivity, folderInfos.get(i).folder_path, IConstants.START_FROM_FOLDER);
                    folderInfos.get(i).folder_count = albumList.size();
                }
                return Observable.fromArray(folderInfos);
            }
        }).subscribe(new Consumer<List<FolderInfo>>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull List<FolderInfo> folderInfos) throws Exception {
                mLocalFolderView.updateFolderInfo(folderInfos);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        });
    }

    @Override
    public void updateFolderInfo(LocalFolderAdapter adapter, List<FolderInfo> folderInfos) {
        adapter.updateData(folderInfos);
    }

    @Override
    public void detachView() {
        mLocalFolderView = null;
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
