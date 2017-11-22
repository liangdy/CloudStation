package com.magical.library.imageloader;

import android.graphics.Bitmap;

import com.enrique.stackblur.NativeBlurProcess;
import com.facebook.imagepipeline.request.BasePostprocessor;

/**
 * Project: CloudStation
 * FileName: FastBlurPostprocessor.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/24/17 11:21 AM
 * Editor: ldy
 * Modify Date: 8/24/17 11:21 AM
 * Remark:
 */
public class FastBlurPostprocessor extends BasePostprocessor {

    private float mRadius;

    public FastBlurPostprocessor(float blurRadius) {
        this.mRadius = blurRadius;
    }

    public void process(Bitmap bitmap) {
        try {
            bitmap.setHasAlpha(true);
            NativeBlurProcess blur = new NativeBlurProcess();
            blur.blur(bitmap, mRadius);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return "FastBlurPostprocessor";
    }
}
