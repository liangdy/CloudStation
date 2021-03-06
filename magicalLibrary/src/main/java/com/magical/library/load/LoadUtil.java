package com.magical.library.load;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;

import com.magical.library.load.core.TargetContext;

/**
 * Project: CloudStation
 * FileName: LoadUtil.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 10/3/17 7:58 PM
 * Editor: ldy
 * Modify Date: 10/3/17 7:58 PM
 * Remark:
 */
public class LoadUtil {

    public static TargetContext getTargetContext(Object target) {
        ViewGroup contentParent;
        Context context;
        if (target instanceof Activity) {
            Activity activity = (Activity) target;
            context = activity;
            contentParent = (ViewGroup) activity.findViewById(android.R.id.content);
        } else if (target instanceof View) {
            View view = (View) target;
            contentParent = (ViewGroup) (view.getParent());
            context = view.getContext();
        } else {
            throw new IllegalArgumentException("The target must be within Activity, Fragment, View.");
        }
        int childIndex = 0;
        int childCount = contentParent == null ? 0 : contentParent.getChildCount();
        View oldContent;
        if (target instanceof View) {
            oldContent = (View) target;
            for (int i = 0; i < childCount; i++) {
                if (contentParent.getChildAt(i) == oldContent) {
                    childIndex = i;
                    break;
                }
            }
        } else {
            oldContent = contentParent != null ? contentParent.getChildAt(0) : null;
        }
        if (oldContent == null) {
            throw new IllegalArgumentException(String.format("enexpected error when register LoadSir in %s", target
                    .getClass().getSimpleName()));
        }
        if (contentParent != null) {
            contentParent.removeView(oldContent);
        }
        return new TargetContext(context, contentParent, oldContent, childIndex);
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
