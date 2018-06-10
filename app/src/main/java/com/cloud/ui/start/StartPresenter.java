package com.cloud.ui.start;

import android.support.annotation.NonNull;

import com.cloud.R;
import com.cloud.ui.BaseActivity;
import com.cloud.utils.ActivityUtils;
import com.magical.library.imageloader.ImageLoader;
import com.magical.library.utils.MagicalLog;
import com.magical.library.view.XImageView;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * Project: CloudStation
 * FileName: StartPresenter.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 2/22/17 4:58 PM
 * Editor: ldy
 * Modify Date: 2/22/17 4:58 PM
 * Remark:
 */
public class StartPresenter implements StartContract.Presenter {

    private static final String TAG = StartPresenter.class.getSimpleName();

    private BaseActivity mActivity;

    private StartContract.View mStartView;

    private Disposable disposable;

    @Inject
    public StartPresenter(BaseActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void onStartClick() {
        //TODO: start page click action
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        ActivityUtils.goMainActivity(mActivity, null);
        mActivity.finish();
    }

    @Override
    public void showStartPage(File file, XImageView imageView) {
        if (file != null && file.exists()) {
            ImageLoader.loadImageByFile(imageView, file);
        } else {
            ImageLoader.loadImageById(imageView, R.drawable.splash);
        }
    }

    @Override
    public void attachView(@NonNull StartContract.View view) {
        mStartView = view;
        loadFile();
    }

    private void loadFile() {
        File file = getSplashFile();
        mStartView.loadImageView(file);
        countDownAction(5);
    }

    private File getSplashFile() {
        //TODO: get splash file action
        File file = null;
        return file;
    }

    @Override
    public void detachView() {
        mStartView = null;
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    /**
     * 倒计时
     *
     * @param time 单位:秒
     */
    private void countDownAction(int time) {
        if (time < 3) {
            time = 3;
        }
        final int countTime = time;
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Long, Integer>() {
                    @Override
                    public Integer apply(Long increaseTime) throws Exception {
                        return countTime - increaseTime.intValue();
                    }
                })
                .take(countTime + 1)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Integer value) {
                        MagicalLog.i(TAG, value + "秒");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        onStartClick();
                    }
                });
    }
}
