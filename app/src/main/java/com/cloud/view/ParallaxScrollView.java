package com.cloud.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

import com.cloud.R;

/**
 * Project: CloudStation
 * FileName: ParallaxScrollView.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/22/17 5:27 PM
 * Editor: ldy
 * Modify Date: 8/22/17 5:27 PM
 * Remark:
 */
public class ParallaxScrollView extends ScrollView {

    private static final String TAG = "ParallaxScrollView";

    private static final float DEFAULT_SCROLL_FACTOR = 0.6f;
    // 滚动因子
    private float mScrollFactor = DEFAULT_SCROLL_FACTOR;

    private static final boolean PRE_HONEYCOMB = Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB;
    private int mBackgroundResId;
    // 要差动的View
    private View mParallaxView;
    private float mlastY;
    // 保存差动View的位置
    private Rect normal;
    // 回弹重置动画
    private Animation contentRsetAnimation;
    // 缩放重置动画
    private Animation scaleResetAnimation;
    private int parallaxViewHeight;
    private int parallaxViewWidth;
    private boolean isAnimation = false;
    // 平移值
    private int parallaxPercent = 0;
    private boolean mBeDragged = false;

    public ParallaxScrollView(Context context) {
        super(context);
        initView(context, null, 0);
    }

    public ParallaxScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs, 0);
    }

    public ParallaxScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs, defStyle);
    }

    private void initView(Context context, AttributeSet attrs, int defStyle) {
        if (isInEditMode()) {
            return;
        }
        if (attrs != null) {
            TypedArray values = context.obtainStyledAttributes(attrs,
                    R.styleable.ParallaxScrollView, defStyle, 0);
            mBackgroundResId = values.getResourceId(
                    R.styleable.ParallaxScrollView_parallaxView, 0);
            mScrollFactor = values.getFloat(
                    R.styleable.ParallaxScrollView_scrollFactor,
                    DEFAULT_SCROLL_FACTOR);
            values.recycle();
        }
        normal = new Rect();
        // 禁止越界效果
        setVerticalFadingEdgeEnabled(false);
    }

    /**
     * 设置需要差动和缩放的View
     *
     * @param resId View的资源id
     */
    public void setParallaxView(int resId) {
        mParallaxView = findViewById(resId);
    }

    /**
     * 设置需要差动和缩放的View
     *
     * @param view
     */
    public void setParallaxView(View view) {
        mParallaxView = view;
    }

    /**
     * 滚动因子,滚动因子决定平移和缩放的程度
     *
     * @param scrollFactor
     */
    public void setScrollFactor(float scrollFactor) {
        mScrollFactor = scrollFactor;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            applyParallax(getScrollY());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                mlastY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (mParallaxView != null && !isAnimation) {
                    if (mlastY == 0) {
                        mlastY = ev.getY();
                    }
                    float curY = ev.getY();
                    int deltaY = (int) (mlastY - curY);
                    mlastY = curY;
                    if (onSwipeListener != null && deltaY > 0) {
                        onSwipeListener.upglide();
                    }
                    // 在顶部
                    if (getScrollY() == 0 && (deltaY < 0 || mBeDragged)) {
                        mBeDragged = true;
                        requestDisallowInterceptTouchEvent(true);
                        applyReLayoutView(deltaY);
                        applyTransformScale(deltaY);
                    }
                }
                if (mBeDragged) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                resetView();
                if (onSwipeListener != null) {
                    onSwipeListener.onSwipeUp();
                }
                break;
            default:
                break;
        }

        return super.onTouchEvent(ev);
    }

    /**
     * 对要差动的视图做缩放
     *
     * @param deltaY
     */
    private void applyTransformScale(int deltaY) {
        ViewGroup.LayoutParams lp = mParallaxView.getLayoutParams();
        if (lp.width <= 0 || lp.height <= 0) {
            lp.width = mParallaxView.getWidth();
            lp.height = mParallaxView.getHeight();
        }
        deltaY = (int) getParallaxViewTransformDelta(deltaY);
        lp.height -= deltaY;
        lp.width -= deltaY;
        if (lp.height <= parallaxViewHeight) {
            lp.height = parallaxViewHeight;
        }
        if (lp.width <= parallaxViewWidth) {
            lp.width = parallaxViewWidth;
        }
        mParallaxView.requestLayout();
//        Log.i(TAG, "scale parallax View ... ");

    }

    /**
     * 获取对差动视图做变换的偏移值
     *
     * @param deltaY
     * @return
     */
    private float getParallaxViewTransformDelta(int deltaY) {
        float delta = deltaY * mScrollFactor / 4;
        return delta;
    }

    /**
     * 平移整个View
     *
     * @param deltaY
     */
    private void applyReLayoutView(int deltaY) {
        View childView = getChildAt(0);
        childView.clearAnimation();
        // 保存view的初始状态
        if (normal.isEmpty()) {
            normal.set(childView.getLeft(), childView.getTop(),
                    childView.getRight(), childView.getBottom());
            parallaxViewHeight = mParallaxView.getHeight();
            parallaxViewWidth = mParallaxView.getWidth();
        }
        deltaY = (int) getParallaxViewTransformDelta(deltaY);
        // 平移View
        childView.layout(childView.getLeft(), childView.getTop() - deltaY,
                childView.getRight(), childView.getBottom() - deltaY);
        if (onSwipeListener != null) {
            onSwipeListener.onSwiping(deltaY);
        }
//        Log.i(TAG, "relayout view...");
    }

    /**
     * 重置View的状态
     */
    private void resetView() {
        mlastY = 0;
        mBeDragged = false;
        if (!normal.isEmpty()) {
//            Log.d(TAG, "reset View");
            final View childView = getChildAt(0);
            childView.clearAnimation();
            int deltaY = normal.top - childView.getTop();
            // 平移动画
            contentRsetAnimation = new TranslateAnimation(0, 0, 0, deltaY);
            contentRsetAnimation.setDuration(200);
            contentRsetAnimation
                    .setAnimationListener(new Animation.AnimationListener() {

                        @Override
                        public void onAnimationStart(Animation animation) {
                            isAnimation = true;
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            childView.clearAnimation();
                            // 重置布局
                            childView.layout(normal.left, normal.top,
                                    normal.right, normal.bottom);
                            normal.setEmpty();
                            isAnimation = false;
                        }
                    });
            childView.startAnimation(contentRsetAnimation);
            // 缩放动画
            scaleResetAnimation = new ParallaxTransformResetAnimation(
                    mParallaxView, parallaxViewHeight, parallaxViewWidth);
            scaleResetAnimation.setDuration(200);

            if (mParallaxView != null) {
                mParallaxView.startAnimation(scaleResetAnimation);
            }
        }
    }

    /**
     * View缩放后的重置动画
     */
    public class ParallaxTransformResetAnimation extends Animation {
        int targetHeight, targetWidth;
        // View的初始化宽高
        int originalHeight, originalWidth;
        // 需要做动画的差值
        int extraHeight, extraWidth;
        View mView;

        /**
         * 差动View状态重置动画
         *
         * @param view
         * @param targetHeight 目标高度
         * @param targetWidth  目标宽度
         */
        protected ParallaxTransformResetAnimation(View view, int targetHeight,
                                                  int targetWidth) {
            this.mView = view;
            this.targetHeight = targetHeight;
            this.targetWidth = targetWidth;
            originalHeight = view.getHeight();
            originalWidth = view.getWidth();
            extraHeight = this.targetHeight - originalHeight;
            extraWidth = this.targetWidth - originalWidth;
        }

        @Override
        protected void applyTransformation(float interpolatedTime,
                                           Transformation t) {

            int newHeight, newWidth;
            // 计算出新的宽高
            newHeight = (int) (targetHeight - extraHeight
                    * (1 - interpolatedTime));
            newWidth = (int) (targetWidth - extraWidth * (1 - interpolatedTime));
            // 为View设置新的布局
            mView.getLayoutParams().height = newHeight;
            mView.getLayoutParams().width = newWidth;
            mView.requestLayout();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mBackgroundResId > 0 && mParallaxView == null) {
            mParallaxView = findViewById(mBackgroundResId);
            mBackgroundResId = 0;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (PRE_HONEYCOMB && mParallaxView != null) {
            mParallaxView.clearAnimation();
        }
        mParallaxView = null;
        super.onDetachedFromWindow();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        applyParallax(t);
    }

    /**
     * 做差动处理
     *
     * @param y
     */
    private void applyParallax(int y) {
        if (mParallaxView != null) {
            int[] location = new int[2];
            mParallaxView.getLocationOnScreen(location);
            // 超出屏幕不做处理
            if (location[1] + mParallaxView.getHeight() < 0) {
                return;
            }
            int bottom = mParallaxView.getBottom();
            int translationY = (int) (y * mScrollFactor);
            // 平移View
            if (PRE_HONEYCOMB) {
                ViewCompat.wrap(mParallaxView).setTranslationY(translationY);
                // ViewCompat.setTranslationY(mParallaxView, translationY);
            } else {
                mParallaxView.setTranslationY(translationY);
            }
            // 计算出平移的百分比
            int percent = (int) ((double) y / (double) bottom * 100);
            parallaxPercent = percent;
            if (onTranslateListener != null) {
                onTranslateListener.onTranslate(percent);
            }
        }
    }

    /**
     * 获取差动的百分比
     *
     * @return
     */
    public int getParallaxPercent() {
        return parallaxPercent;
    }

    public void setOnTranslateListener(OnTranslateListener onTranslateListener) {
        this.onTranslateListener = onTranslateListener;
    }

    private OnTranslateListener onTranslateListener;

    /**
     * 平移监听器
     */
    public interface OnTranslateListener {

        public void onTranslate(int percent);

    }

    private OnSwipeListener onSwipeListener;

    /**
     * @param onSwipeListener
     */
    public void setOnSwipeListener(OnSwipeListener onSwipeListener) {
        this.onSwipeListener = onSwipeListener;
    }

    /**
     * 滑动监听器，在ScrollView滑动到顶部还继续往下滑动的时候回调
     */
    public interface OnSwipeListener {
        /**
         * 正在滑动
         *
         * @param deltaY 滑动的位移值
         */
        void onSwiping(float deltaY);

        /**
         * 滑动停止后，抬起手指时调用
         */
        void onSwipeUp();

        void upglide();
    }

}
