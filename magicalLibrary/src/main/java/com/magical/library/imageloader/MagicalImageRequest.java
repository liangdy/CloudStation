package com.magical.library.imageloader;

import android.net.Uri;
import android.support.annotation.Nullable;

import com.facebook.imagepipeline.request.ImageRequest;

import javax.annotation.concurrent.Immutable;

/**
 * Project: TShow
 * FileName: MagicalImageRequest.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
@Immutable
public class MagicalImageRequest extends ImageRequest {
    private final String cacheKey;


    public static MagicalImageRequest fromUri(@Nullable Uri uri, String cacheKey) {
        return uri == null
                ? null
                : MagicalImageRequestBuilder.newBuilderWithSource(uri, cacheKey)
                .build();
    }


    public static MagicalImageRequest fromUri(@Nullable String uriString, String cacheKey) {
        return uriString != null && uriString.length() != 0 ? fromUri(Uri
                .parse(uriString), cacheKey) : null;
    }


    protected MagicalImageRequest(MagicalImageRequestBuilder builder) {
        super(builder.getImageRequestBuilder());
        cacheKey = builder.getCacheKey();
    }


    public String getCacheKey() {
        return cacheKey;
    }
}