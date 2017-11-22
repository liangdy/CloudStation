package com.cloud.utils;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import javax.inject.Inject;

/**
 * Project: CloudStation
 * FileName: WindowAnimUtil.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/28/17 10:48 AM
 * Editor: ldy
 * Modify Date: 8/28/17 10:48 AM
 * Remark:
 */
public class WindowAnimUtil {
    
    private ValueAnimator valueAnimator;
    private UpdateListener updateListener;
    private EndListener endListener;
    private long duration;
    private float start;
    private float end;
    private Interpolator interpolator = new LinearInterpolator();

    @Inject
    public WindowAnimUtil() {
        duration = 200; //默认动画时常1s
        start = 0.0f;
        end = 1.0f;
        interpolator = new LinearInterpolator();// 匀速的插值器
    }


    public void setDuration(int timeLength) {
        duration = timeLength;
    }

    public void setValueAnimator(float start, float end, long duration) {

        this.start = start;
        this.end = end;
        this.duration = duration;

    }

    public void setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
    }

    public void startAnimator() {
        if (valueAnimator != null) {
            valueAnimator = null;
        }
        valueAnimator = ValueAnimator.ofFloat(start, end);
        valueAnimator.setDuration(duration);
        valueAnimator.setInterpolator(interpolator);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                if (updateListener == null) {
                    return;
                }

                float cur = (float) valueAnimator.getAnimatedValue();
                updateListener.progress(cur);
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (endListener == null) {
                    return;
                }
                endListener.endUpdate(animator);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        valueAnimator.start();
    }

    public void addUpdateListener(UpdateListener updateListener) {

        this.updateListener = updateListener;
    }

    public void addEndListner(EndListener endListener) {
        this.endListener = endListener;
    }

    public interface EndListener {
        void endUpdate(Animator animator);
    }

    public interface UpdateListener {

        void progress(float progress);
    }
}
