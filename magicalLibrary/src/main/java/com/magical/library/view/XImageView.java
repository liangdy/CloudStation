package com.magical.library.view;

import android.content.Context;
import android.util.AttributeSet;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Project: CloudStation
 * FileName: XImageView.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/12/17 12:47 AM
 * Editor: ldy
 * Modify Date: 3/12/17 12:47 AM
 * Remark:
 */
public class XImageView extends SimpleDraweeView {
    public XImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public XImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public XImageView(Context context) {
        super(context);
    }


    public XImageView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
    }
}
