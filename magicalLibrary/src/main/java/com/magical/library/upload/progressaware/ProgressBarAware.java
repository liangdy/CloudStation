package com.magical.library.upload.progressaware;

import android.view.View;
import android.widget.ProgressBar;

/**
 * Project: TShow
 * FileName: ProgressBarAware.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
public class ProgressBarAware extends BaseViewAware {

    public ProgressBarAware(ProgressBar view) {
        super(view);
    }

    @Override
    public void setProgress(int progress, View view) {
        ProgressBar pb = ((ProgressBar) view);
        pb.setProgress(progress);
        pb.invalidate();

    }
}