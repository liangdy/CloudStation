package com.magical.library.upload.progressaware;

import android.os.Looper;
import android.view.View;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Project: TShow
 * FileName: BaseViewAware.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
public abstract class BaseViewAware implements ProgressAware {

    protected Reference<View> mViewRef;

    public BaseViewAware(View view) {
        mViewRef = new WeakReference<View>(view);
    }

    @Override
    public int getId() {
        View view = mViewRef.get();
        if(view == null)
            return super.hashCode();
        else
            return view.hashCode();
    }

    @Override
    public boolean isCollected() {
        return mViewRef.get() == null;
    }

    @Override
    public boolean setProgress(int progress) {
        if(Looper.myLooper() == Looper.getMainLooper()) {
            View view = mViewRef.get();
            if(view != null) {
                setProgress(progress, view);
                return true;
            }
        }
        return false;
    }

    @Override
    public View getWrappedView() {
        return mViewRef.get();
    }

    @Override
    public void setVisibility(int visibility) {
        View view = mViewRef.get();
        if(view != null) {
            view.setVisibility(visibility);
        }
    }

    public abstract void setProgress(int progress, View view);

}