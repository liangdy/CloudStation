package com.cloud.ui.music.song;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.cloud.api.CloudApi;
import com.cloud.dialog.CategoryDialog;
import com.cloud.model.music.AllSongSheet;
import com.cloud.model.music.SongSheetCategory;
import com.cloud.ui.BaseActivity;
import com.cloud.ui.adapter.AllSongSheetAdapter;
import com.cloud.ui.music.detail.songsheet.SongSheetDetailActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Project: CloudStation
 * FileName: SongListPresenter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/22/17 10:47 AM
 * Editor: ldy
 * Modify Date: 3/22/17 10:47 AM
 * Remark:
 */
public class SongListPresenter implements SongListContract.Presenter {

    private SongListContract.View mSongListView;

    public BaseActivity mActivity;

    private Disposable mDisposable;

    private int page = 1;

    private List<AllSongSheet.ContentBean> mAllSongSheets = new ArrayList<>();
    private List<SongSheetCategory.ContentBean> mSongSheetCategorys = new ArrayList<>();

    @Inject
    public SongListPresenter(BaseActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void attachView(@NonNull SongListContract.View view) {
        mAllSongSheets.clear();
        mSongListView = view;
        mSongListView.initialized();
        getAllSongSheet(page);
    }

    @Override
    public void detachView() {
        mSongListView = null;
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    public void refresh() {
        page = 1;
        mAllSongSheets.clear();
        getAllSongSheet(page);
    }

    @Override
    public void loadMore() {
        page++;
        getAllSongSheet(page);
    }

    @Override
    public void getAllSongSheet(int num) {
        CloudApi.getAllSongSheet(15, num).observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mDisposable = disposable;
                    }
                }).subscribe(new Consumer<AllSongSheet>() {
            @Override
            public void accept(AllSongSheet allSongSheet) throws Exception {
                mAllSongSheets.addAll(allSongSheet.content);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("throwable:" + throwable.toString());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                mSongListView.loadAllSongSheet(mAllSongSheets);
                mSongListView.loadComplete();
            }
        });
    }

    @Override
    public void updateSongSheet(AllSongSheetAdapter adapter, List<AllSongSheet.ContentBean> contentBeen) {
        adapter.updateData(contentBeen);
    }

    @Override
    public void getAllSongCategory() {
        CloudApi.getSongSheetCategory().observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mDisposable = disposable;
                    }
                })
                .subscribe(new Consumer<SongSheetCategory>() {
                    @Override
                    public void accept(SongSheetCategory songSheetCategory) throws Exception {
                        mSongSheetCategorys.addAll(songSheetCategory.content);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("throwable:" + throwable);
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        mSongListView.loadSongSheetCategory(mSongSheetCategorys);
                    }
                });
    }

    @Override
    public void updateSongCategory(List<SongSheetCategory.ContentBean> songSheetCategories) {
        if (!songSheetCategories.isEmpty()) {
            songSheetCategories.remove(0);
            CategoryDialog dialog = new CategoryDialog(mActivity);
            dialog.setCategories(songSheetCategories);
            dialog.show();
        }
    }

    @Override
    public void goSongSheetDetail(int position) {
        Intent intent = new Intent(mActivity, SongSheetDetailActivity.class);
        intent.putExtra("listid", mAllSongSheets.get(position).listid);
        mActivity.startActivity(intent);
    }
}
