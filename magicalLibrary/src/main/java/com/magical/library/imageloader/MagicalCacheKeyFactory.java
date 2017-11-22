package com.magical.library.imageloader;

import android.text.TextUtils;

import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.request.ImageRequest;

import javax.annotation.Nullable;

/**
 * Project: TShow
 * FileName: MagicalCacheKeyFactory.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
public class MagicalCacheKeyFactory extends DefaultCacheKeyFactory {

    private static MagicalCacheKeyFactory sInstance;


    public static synchronized MagicalCacheKeyFactory getInstance() {
        if (sInstance == null) {
            sInstance = new MagicalCacheKeyFactory();
        }
        return sInstance;
    }


    private MagicalCacheKeyFactory() {
    }

    @Override
    public CacheKey getEncodedCacheKey(ImageRequest request, @Nullable Object callerContext) {
        if (request instanceof MagicalImageRequest) {
            MagicalImageRequest openImageRequest = (MagicalImageRequest) request;
            String openCacheKey = getOpenCacheKey(openImageRequest);
            if (!TextUtils.isEmpty(openCacheKey)) {
                return new SimpleCacheKey(openCacheKey);
            }
        }
        return super.getEncodedCacheKey(request, callerContext);
    }

    public String getOpenCacheKey(MagicalImageRequest imageRequest) {
        return imageRequest.getCacheKey();
    }
}