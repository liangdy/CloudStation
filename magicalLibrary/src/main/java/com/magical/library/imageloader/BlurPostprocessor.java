package com.magical.library.imageloader;

import android.graphics.Bitmap;

import com.facebook.imagepipeline.request.BasePostprocessor;
import com.magical.library.MagicalApplication;
import com.magical.library.utils.BitmapUtils;

/**
 * Project: CloudStation
 * FileName: BlurPostprocessor.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 8/24/17 10:47 AM
 * Editor: ldy
 * Modify Date: 8/24/17 10:47 AM
 * Remark:
 */
public class BlurPostprocessor extends BasePostprocessor {

    @Override
    public String getName() {
        return "FuzzyPostprocessor";
    }

    @Override
    public void process(Bitmap bitmap) {
        BitmapUtils.blur(MagicalApplication.getContext(), bitmap, 50);
    }
}