package com.magical.library.imageloader;

import android.net.Uri;
import android.support.annotation.Nullable;

import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;

/**
 * Project: TShow
 * FileName: MagicalImageRequestBuilder.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
public class MagicalImageRequestBuilder {

    private String mCacheKey;
    private ImageRequestBuilder mImageRequestBuilder;

    private MagicalImageRequestBuilder(ImageRequestBuilder imageRequestBuilder, String cacheKey) {
        this.mImageRequestBuilder = imageRequestBuilder;
        this.mCacheKey = cacheKey;
    }

    /**
     * Creates a new request builder instance. The setting will be done according to the source type.
     *
     * @param uri      the uri to fetch
     * @param cacheKey 缓存KEY
     * @return a new request builder instance
     */
    public static MagicalImageRequestBuilder newBuilderWithSource(Uri uri, String cacheKey) {
        ImageRequestBuilder imageRequestBuilder = ImageRequestBuilder.newBuilderWithSource(uri);
        return new MagicalImageRequestBuilder(imageRequestBuilder, cacheKey);
    }

    /**
     * Creates a new request builder instance for a local resource image.
     * <p/>
     * <p>Only image resources can be used with the image pipeline (PNG, JPG, GIF). Other resource
     * types such as Strings or XML Drawables make no sense in the context of the image pipeline and
     * so cannot be supported. Attempts to do so will throw an
     * {@link IllegalArgumentException} when the pipeline tries to decode the resource.
     * <p/>
     * <p>One potentially confusing case is drawable declared in XML (e.g. ShapeDrawable). This is not
     * an image. If you want to display an XML drawable as the main image, then set it as a
     * placeholder and do not set a URI.
     * <p/>
     *
     * @param resId    local image resource id.
     * @param cacheKey 缓存KEY
     * @return a new request builder instance.
     */
    public static MagicalImageRequestBuilder newBuilderWithResourceId(int resId, String cacheKey) {
        ImageRequestBuilder imageRequestBuilder = ImageRequestBuilder.newBuilderWithResourceId(resId);
        return new MagicalImageRequestBuilder(imageRequestBuilder, cacheKey);
    }

    /**
     * Creates a new request builder instance with the same parameters as the imageRequest passed in.
     *
     * @param imageRequest the ImageRequest from where to copy the parameters to the builder.
     * @return a new request builder instance
     */
    public static MagicalImageRequestBuilder fromRequest(MagicalImageRequest
                                                                 imageRequest) {
        ImageRequestBuilder imageRequestBuilder = ImageRequestBuilder.newBuilderWithSource(imageRequest.getSourceUri())
                .setAutoRotateEnabled(imageRequest.getAutoRotateEnabled())
                .setImageDecodeOptions(imageRequest.getImageDecodeOptions())
                .setCacheChoice(imageRequest.getCacheChoice()).
                        setLocalThumbnailPreviewsEnabled(imageRequest.getLocalThumbnailPreviewsEnabled()).
                        setLowestPermittedRequestLevel(imageRequest.getLowestPermittedRequestLevel()).
                        setPostprocessor(imageRequest.getPostprocessor()).
                        setProgressiveRenderingEnabled(imageRequest.getProgressiveRenderingEnabled()).
                        setRequestPriority(imageRequest.getPriority()).
                        setResizeOptions(imageRequest.getResizeOptions());
        return new MagicalImageRequestBuilder(imageRequestBuilder, imageRequest.getCacheKey());
    }

    public ImageRequestBuilder getImageRequestBuilder() {
        return mImageRequestBuilder;
    }

    public String getCacheKey() {
        return mCacheKey;
    }

    /**
     * Sets the source uri (both network and local uris are supported).
     * Note: this will enable disk caching for network sources, and disable it for local sources.
     *
     * @param uri the uri to fetch the image from
     * @return the updated builder instance
     */
    public MagicalImageRequestBuilder setSource(Uri uri) {
        getImageRequestBuilder().setSource(uri);
        return this;
    }

    /**
     * Gets the source Uri.
     */
    public Uri getSourceUri() {
        return getImageRequestBuilder().getSourceUri();
    }

    /**
     * Sets the lowest level that is permitted to request the image from.
     *
     * @param requestLevel the lowest request level that is allowed
     * @return the updated builder instance
     */
    public MagicalImageRequestBuilder setLowestPermittedRequestLevel(ImageRequest.RequestLevel requestLevel) {
        getImageRequestBuilder().setLowestPermittedRequestLevel(requestLevel);
        return this;
    }

    /**
     * Gets the lowest permitted request level.
     */
    public ImageRequest.RequestLevel getLowestPermittedRequestLevel() {
        return getImageRequestBuilder().getLowestPermittedRequestLevel();
    }

    /**
     * Enables or disables auto-rotate for the image in case image has orientation.
     *
     * @param enabled
     * @return the updated builder instance
     */
    public MagicalImageRequestBuilder setAutoRotateEnabled(boolean enabled) {
        getImageRequestBuilder().setAutoRotateEnabled(enabled);
        return this;
    }

    /**
     * Returns whether auto-rotate is enabled.
     */
//    public boolean isAutoRotateEnabled() {
//        return getImageRequestBuilder().isAutoRotateEnabled();
//    }

    /**
     * Sets resize options in case resize should be performed.
     *
     * @param resizeOptions resize options
     * @return the modified builder instance
     */
    public MagicalImageRequestBuilder setResizeOptions(ResizeOptions resizeOptions) {
        getImageRequestBuilder().setResizeOptions(resizeOptions);
        return this;
    }

    /**
     * Gets the resize options if set, null otherwise.
     */
    @Nullable
    public ResizeOptions getResizeOptions() {
        return getImageRequestBuilder().getResizeOptions();
    }


    public MagicalImageRequestBuilder setImageDecodeOptions(ImageDecodeOptions imageDecodeOptions) {
        getImageRequestBuilder().setImageDecodeOptions(imageDecodeOptions);
        return this;
    }

    public ImageDecodeOptions getImageDecodeOptions() {
        return getImageRequestBuilder().getImageDecodeOptions();
    }

    /**
     * Sets the cache choice. Pipeline might use different caches and eviction policies for each
     * image type.
     *
     * @param cacheChoice the cache choice to set
     * @return the modified builder instance
     */
    public MagicalImageRequestBuilder setCacheChoice(ImageRequest.CacheChoice cacheChoice) {
        getImageRequestBuilder().setCacheChoice(cacheChoice);
        return this;
    }

    /**
     * Gets the image type (profile image or default).
     */
    public ImageRequest.CacheChoice getCacheChoice() {
        return getImageRequestBuilder().getCacheChoice();
    }

    /**
     * Enables or disables progressive rendering.
     *
     * @param enabled
     * @return the modified builder instance
     */
    public MagicalImageRequestBuilder setProgressiveRenderingEnabled(boolean enabled) {
        getImageRequestBuilder().setProgressiveRenderingEnabled(enabled);
        return this;
    }

    /**
     * Returns whether progressive loading is enabled.
     */
    public boolean isProgressiveRenderingEnabled() {
        return getImageRequestBuilder().isProgressiveRenderingEnabled();
    }

    /**
     * Enables or disables the use of local thumbnails as previews.
     *
     * @param enabled
     * @return the modified builder instance
     */
    public MagicalImageRequestBuilder setLocalThumbnailPreviewsEnabled(boolean enabled) {
        getImageRequestBuilder().setLocalThumbnailPreviewsEnabled(enabled);
        return this;
    }

    /**
     * Returns whether the use of local thumbnails for previews is enabled
     */
    public boolean isLocalThumbnailPreviewsEnabled() {
        return getImageRequestBuilder().isLocalThumbnailPreviewsEnabled();
    }

    /**
     * Returns whether the use of the disk cache is enabled
     */
    public boolean isDiskCacheEnabled() {
        return getImageRequestBuilder().isDiskCacheEnabled();
    }

    /**
     * Set priority for the request.
     *
     * @param requestPriority
     * @return the modified builder instance
     */
    public MagicalImageRequestBuilder setRequestPriority(Priority requestPriority) {
        getImageRequestBuilder().setRequestPriority(requestPriority);
        return this;
    }

    /**
     * Returns the request priority
     */
    public Priority getRequestPriority() {
        return getImageRequestBuilder().getRequestPriority();
    }

    /**
     * Sets the postprocessor.
     *
     * @param postprocessor postprocessor to postprocess the output bitmap with.
     */
    public MagicalImageRequestBuilder setPostprocessor(Postprocessor postprocessor) {
        getImageRequestBuilder().setPostprocessor(postprocessor);
        return this;
    }

    /**
     * Gets postprocessor if set, null otherwise.
     */
    @Nullable
    public Postprocessor getPostprocessor() {
        return getImageRequestBuilder().getPostprocessor();
    }

    /**
     * Builds the Request.
     *
     * @return a valid image request
     */
    public MagicalImageRequest build() {
        getImageRequestBuilder().build();
        return new MagicalImageRequest(this);
    }
}