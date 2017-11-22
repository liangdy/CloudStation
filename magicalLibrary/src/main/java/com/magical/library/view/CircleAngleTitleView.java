package com.magical.library.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.TextView;

import com.magical.library.R;

/**
 * Project: TShow
 * FileName: CircleAngleTitleView.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:59 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:59 PM
 * Remark:
 */
public class CircleAngleTitleView extends TextView {

    private Paint paintBg, paintFr;
    private float frameWidth = 0;
    private float radiusOfCorner = 0;
    private int backColor, frameColor;


    public CircleAngleTitleView(Context context) {
        super(context);
        init(null, 0);
    }


    public CircleAngleTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }


    public CircleAngleTitleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }


    public void setRadiusOfCorner(int fw) {
        frameWidth = fw;
        invalidate();
    }


    public void setCircle(int rc) {
        radiusOfCorner = rc;
        invalidate();
    }


    public void setFrameColor(int color) {
        frameColor = color;
        invalidate();
    }


    public void setAllColor(int color) {
        frameColor = color;
        backColor = color;
        invalidate();
    }


    public void setAllColor(int color, int fcolor) {
        frameColor = fcolor;
        backColor = color;
        invalidate();
    }


    public void setAllColor(int color, int fcolor, int textColor) {
        frameColor = fcolor;
        backColor = color;
        setTextColor(textColor);
    }


    private void init(AttributeSet attrs, int defStyle) {
        TypedArray a = getContext()
                .obtainStyledAttributes(attrs, R.styleable.CircleAngleTitleView, defStyle, 0);
        if (null != a) {
            radiusOfCorner = a
                    .getDimension(R.styleable.CircleAngleTitleView_radiusOfCorner, radiusOfCorner);
            frameWidth = a
                    .getDimension(R.styleable.CircleAngleTitleView_frameWidth, frameWidth);
            backColor = a
                    .getColor(R.styleable.CircleAngleTitleView_backColor, backColor);
            frameColor = a
                    .getColor(R.styleable.CircleAngleTitleView_frameColor, frameColor);
            a.recycle();
        }
        this.setBackgroundColor(Color.TRANSPARENT);
    }


    private void initPaintFr() {
        paintFr = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintFr.setStyle(Paint.Style.STROKE);
        paintFr.setColor(frameColor);
        paintFr.setStrokeWidth(frameWidth);
    }


    private void initPaintBg() {
        paintBg = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBg.setStyle(Paint.Style.FILL);
        paintBg.setColor(backColor);
        paintBg.setStrokeWidth(frameWidth);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (frameWidth == 0) {
            frameWidth = 2;
        }
        int h = getHeight();
        int w = getWidth();
        RectF rect = new RectF(
                frameWidth / 2,
                frameWidth / 2, w - frameWidth / 2, h - frameWidth / 2);
        if (backColor != 0) {
            initPaintBg();
            if (radiusOfCorner > 0) {
                canvas.drawRoundRect(rect, radiusOfCorner, radiusOfCorner, paintBg);
            } else {
                canvas.drawRoundRect(rect, h / 2, h / 2, paintBg);
            }
        }
        if (frameColor == 0) {
            frameColor = this.getCurrentTextColor();
        }
        initPaintFr();
        if (radiusOfCorner > 0) {
            canvas.drawRoundRect(rect, radiusOfCorner, radiusOfCorner, paintFr);
        } else {
            canvas.drawRoundRect(rect, h / 2, h / 2, paintFr);
        }
        super.onDraw(canvas);
    }
}
