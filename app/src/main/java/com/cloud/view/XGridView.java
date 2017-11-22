package com.cloud.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Project: CloudStation
 * FileName: XGridView.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 3/20/17 11:24 AM
 * Editor: ldy
 * Modify Date: 3/20/17 11:24 AM
 * Remark:
 */
public class XGridView extends GridView {

    public XGridView(Context context) {
        super(context);
    }

    public XGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
