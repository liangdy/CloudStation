package com.magical.library.upload.progressaware;

import android.view.View;

/**
 * Project: TShow
 * FileName: ProgressAware.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
public interface ProgressAware {

    public int getId();

    /**
     * 是否被回收
     *
     * @return
     */
    public boolean isCollected();

    /**
     * 设置进度
     *
     * @param progress
     */
    public boolean setProgress(int progress);

    public View getWrappedView();

    public void setVisibility(int visibility);

}
