package com.magical.library.load.core;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Project: CloudStation
 * FileName: TargetContext.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 10/3/17 8:04 PM
 * Editor: ldy
 * Modify Date: 10/3/17 8:04 PM
 * Remark:
 */
public class TargetContext {
    private Context context;
    private ViewGroup parentView;
    private View oldContent;
    private int childIndex;

    public TargetContext(Context context, ViewGroup parentView, View oldContent, int childIndex) {
        this.context = context;
        this.parentView = parentView;
        this.oldContent = oldContent;
        this.childIndex = childIndex;
    }

    public Context getContext() {
        return context;
    }

    View getOldContent() {
        return oldContent;
    }

    int getChildIndex() {
        return childIndex;
    }

    ViewGroup getParentView() {
        return parentView;
    }
}
