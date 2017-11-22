package com.cloud.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.cloud.R;

/**
 * Project: CloudStation
 * FileName: SpeedSelectView.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/14/17 4:46 PM
 * Editor: ldy
 * Modify Date: 8/14/17 4:46 PM
 * Remark:
 */
public class SpeedSelectView extends LinearLayout {

    public SpeedSelectView(Context context) {
        super(context);
        init();
    }

    public SpeedSelectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SpeedSelectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        removeAllViews();
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        setBackgroundColor(Color.TRANSPARENT);
        setData(new String[]{"注册", "完善信息", "上传", "跑路"});
        setSpeedProgress(4);
    }

    public void setData(String[] data) {
        if (null != data && data.length > 0) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (60 * getResources().getDisplayMetrics().density),
                    (int) (35 * getResources().getDisplayMetrics().density));
            params.weight = 1;
            for (int i = 0; i < data.length; i++) {
                SpeedView item = new SpeedView(getContext());
                if (i == 0) {
                    item.setLeftLine(false);
                } else {
                    item.setLeftLine(true);
                }
                if (i == data.length - 1) {
                    item.setRightLine(false);
                } else {
                    item.setRightLine(true);
                }
                item.setLayoutParams(params);
                item.setText(data[i]);
                this.addView(item);
            }
        }
    }

    public void setSpeedProgress(int sp) {
        if (sp < 0) {
            sp = 0;
        }
        if (sp > getChildCount()) {
            sp = getChildCount();
        }
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) instanceof SpeedView) {
                SpeedView sv = (SpeedView) getChildAt(i);
                if (i < sp) {
                    if (i < sp - 1 || i == getChildCount() - 1) {
                        sv.setSelctAll();
                    } else {
                        sv.setSelctHalf();
                    }
                } else {
                    sv.setUnSelect();
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        int cwh = (int) (15 * getResources().getDisplayMetrics().density);
        int count = getWidth() / cwh;
        canvas.drawRoundRect(new RectF(0, 0, getWidth(), getHeight() - (cwh / 2)), 0, 0, paint);
        for (int i = 0; i <= count; i++) {
            canvas.drawRoundRect(new RectF(i * cwh, getHeight() - cwh, i * cwh + cwh, getHeight()), cwh, cwh, paint);
        }
        super.onDraw(canvas);
    }

    private class SpeedView extends android.support.v7.widget.AppCompatTextView {

        private int unSeletColor = Color.parseColor("#aaaaaa");
        private int selectColor = getResources().getColor(R.color.red);

        private boolean seletLeft = false, seletRight = false;

        public void setLeftLine(boolean leftLine) {
            this.leftLine = leftLine;
            invalidate();
        }

        public void setRightLine(boolean rightLine) {
            this.rightLine = rightLine;
            invalidate();
        }

        private boolean leftLine = true;
        private boolean rightLine = true;

        public SpeedView(Context context) {
            super(context);
            init();
        }

        public SpeedView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public SpeedView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        private void init() {
            setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            setUnSelect();
        }

        public void setUnSelect() {
            seletLeft = false;
            seletRight = false;
            setTextColor(unSeletColor);
            invalidate();
        }

        public void setSelctHalf() {
            seletLeft = true;
            seletRight = false;
            setTextColor(selectColor);
            invalidate();
        }

        public void setSelctAll() {
            seletLeft = true;
            seletRight = true;
            setTextColor(selectColor);
            invalidate();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(seletLeft ? selectColor : unSeletColor);
            int cwh = (int) (10 * getResources().getDisplayMetrics().density);
            int lwh = (int) (1 * getResources().getDisplayMetrics().density);
            int cl = (getWidth() - cwh) / 2;
            int ll = (cwh - lwh) / 2;
            canvas.drawRoundRect(new RectF(cl, 0, cl + cwh, cwh), cwh, cwh, paint);
            if (leftLine) {
                canvas.drawRoundRect(new RectF(0, ll, cl, ll + lwh), 0, 0, paint);
            }
            paint.setColor(seletRight ? selectColor : unSeletColor);
            if (rightLine) {
                canvas.drawRoundRect(new RectF(cl + cwh, ll, getWidth(), ll + lwh), 0, 0, paint);
            }
        }
    }
}
