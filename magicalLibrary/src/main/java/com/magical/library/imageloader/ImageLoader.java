package com.magical.library.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.magical.library.MagicalApplication;
import com.magical.library.R;
import com.magical.library.utils.EncryptUtils;
import com.magical.library.utils.MagicalLog;
import com.magical.library.utils.StringUtils;

import java.io.File;

/**
 * Project: TShow
 * FileName: ImageLoader.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
public class ImageLoader {

    private static final String TAG = ImageLoader.class.getSimpleName();

    private static final int DEFUALT_PLACE_IMAGE_RID = R.drawable.dialog_default_bg;
    private static final int DEFUALT_FAILURE_IMAGE_RID = R.drawable.dialog_default_bg;
    private static final int DEFUALT_RETRY_IMAGE_RID = R.drawable.dialog_default_bg;


    public static void loadImageById(SimpleDraweeView view, int resId) {
        Uri uri = Uri.parse("res:///" + resId);
        view.setImageURI(uri);
    }

    public static void loadImageById(SimpleDraweeView view, int resId, boolean isCircle) {
        Uri uri = Uri.parse("res:///" + resId);
        view.setImageURI(uri);
        FrescoConfigConstants.setCircle(view, isCircle);
    }

    public static void loadImageByFile(SimpleDraweeView view, File file) {
        Uri uri = Uri.parse("file://" + file.getAbsolutePath());
        view.setImageURI(uri);
    }

    public static void loadImageByUri(SimpleDraweeView view, Uri uri) {
        view.setImageURI(uri);
    }

    public static void loadImageByFile(SimpleDraweeView view, File file, boolean isCircle) {
        Uri uri = Uri.parse("file://" + file.getAbsolutePath());
        view.setImageURI(uri);
        FrescoConfigConstants.setCircle(view, isCircle);
    }

    public static void loadImageByPath(SimpleDraweeView view, String path) {
        Uri uri = Uri.parse("file://" + path);
        view.setImageURI(uri);
    }

    public static void loadImageByPath(SimpleDraweeView view, String path, boolean isCircle) {
        Uri uri = Uri.parse("file://" + path);
        view.setImageURI(uri);
        FrescoConfigConstants.setCircle(view, isCircle);
    }

    public static void loadImageByAssets(SimpleDraweeView view, String fileName) {
        Uri uri = Uri.parse("assets://" + fileName);
        view.setImageURI(uri);
    }

    public static void loadImageByAssets(SimpleDraweeView view, String fileName, boolean isCircle) {
        Uri uri = Uri.parse("assets://" + fileName);
        view.setImageURI(uri);
        FrescoConfigConstants.setCircle(view, isCircle);
    }

    public static void loadImageByBitmap(Context context, SimpleDraweeView view, Bitmap bitmap) {
        Uri uri = Uri.parse(MediaStore.Images.Media
                .insertImage(context.getContentResolver(), bitmap, null, null));
        view.setImageURI(uri);
    }

    public static void loadImageByBitmap(Context context, SimpleDraweeView view, Bitmap bitmap, boolean isCircle) {
        Uri uri = Uri.parse(MediaStore.Images.Media
                .insertImage(context.getContentResolver(), bitmap, null, null));
        view.setImageURI(uri);
        FrescoConfigConstants.setCircle(view, isCircle);
    }

    public static void loadImage(SimpleDraweeView view, String url) {
        if (StringUtils.isNotEmpty(url)) {
            MagicalLog.t(TAG);
            MagicalLog.i("view-width:" + (view.getLayoutParams() != null ? view
                    .getLayoutParams().width : -1) + " view-height:" +
                    (view.getLayoutParams() != null ? view
                            .getLayoutParams().height : -1) + "  url：" + url);
            //setHierarchyDefaultDrawable(view);
            FrescoConfigConstants
                    .loadImage(view, url, EncryptUtils.getMD5(url), false, ImageRequest.CacheChoice.DEFAULT);
        }
    }

    public static void loadImage(SimpleDraweeView view, String url, boolean isCircle) {
        if (StringUtils.isNotEmpty(url)) {
            MagicalLog.t(TAG);
            MagicalLog.i("view-width:" + (view.getLayoutParams() != null ? view
                    .getLayoutParams().width : -1) + " view-height:" +
                    (view.getLayoutParams() != null ? view
                            .getLayoutParams().height : -1) + "  url：" + url);
            //setHierarchyDefaultDrawable(view);
            FrescoConfigConstants
                    .loadImage(view, url, EncryptUtils.getMD5(url), false, ImageRequest.CacheChoice.DEFAULT, isCircle);
        }
    }

    public static void loadImage(SimpleDraweeView view, String url, String cacheKey) {
        if (StringUtils.isNotEmpty(url)) {
            MagicalLog.t(TAG);
            MagicalLog.i("view-width:" + (view.getLayoutParams() != null ? view
                    .getLayoutParams().width : -1) + " view-height:" +
                    (view.getLayoutParams() != null ? view
                            .getLayoutParams().height : -1) + "  url：" + url);
            //setHierarchyDefaultDrawable(view);
            FrescoConfigConstants
                    .loadImage(view, url, cacheKey, false, ImageRequest.CacheChoice.DEFAULT);
        }
    }

    public static void loadImage(SimpleDraweeView view, String url, String cacheKey, boolean isCircle) {
        if (StringUtils.isNotEmpty(url)) {
            MagicalLog.t(TAG);
            MagicalLog.i("view-width:" + (view.getLayoutParams() != null ? view
                    .getLayoutParams().width : -1) + " view-height:" +
                    (view.getLayoutParams() != null ? view
                            .getLayoutParams().height : -1) + "  url：" + url);
            //setHierarchyDefaultDrawable(view);
            FrescoConfigConstants
                    .loadImage(view, url, cacheKey, false, ImageRequest.CacheChoice.DEFAULT, isCircle);
        }
    }

    public static void loadImage(SimpleDraweeView view, String url, String cacheKey, ImageRequest.CacheChoice cacheChoice) {
        if (StringUtils.isNotEmpty(url)) {
            MagicalLog.t(TAG);
            MagicalLog.i("view-width:" + (view.getLayoutParams() != null ? view
                    .getLayoutParams().width : -1) + " view-height:" +
                    (view.getLayoutParams() != null ? view
                            .getLayoutParams().height : -1) + "  url：" + url);
            //setHierarchyDefaultDrawable(view);
            FrescoConfigConstants
                    .loadImage(view, url, cacheKey, false, cacheChoice);
        }
    }

    public static void loadImage(SimpleDraweeView view, String url, String cacheKey, ImageRequest.CacheChoice cacheChoice, boolean isCircle) {
        if (StringUtils.isNotEmpty(url)) {
            MagicalLog.t(TAG);
            MagicalLog.i("view-width:" + (view.getLayoutParams() != null ? view
                    .getLayoutParams().width : -1) + " view-height:" +
                    (view.getLayoutParams() != null ? view
                            .getLayoutParams().height : -1) + "  url：" + url);
            //setHierarchyDefaultDrawable(view);
            FrescoConfigConstants
                    .loadImage(view, url, cacheKey, false, cacheChoice, isCircle);
        }
    }


    /*public static GenericDraweeHierarchy setHierarchyDefaultDrawable(SimpleDraweeView view) {
        return setHierarchy(view, getDrawable(DEFUALT_PLACE_IMAGE_RID), getDrawable(DEFUALT_FAILURE_IMAGE_RID), getDrawable(DEFUALT_RETRY_IMAGE_RID));
    }*/


    public static GenericDraweeHierarchy setHierarchy(SimpleDraweeView view, Drawable placeholderDrawable, Drawable failureDrawable, Drawable retryDrawable) {
        GenericDraweeHierarchy hierarchy;
        if (view.hasHierarchy()) {
            hierarchy = view.getHierarchy();
        } else {
            hierarchy = new GenericDraweeHierarchyBuilder(view.getResources())
                    .setFailureImage(failureDrawable)
                    .setRetryImage(retryDrawable).build();
            view.setHierarchy(hierarchy);
        }
        hierarchy.setPlaceholderImage(placeholderDrawable);
        return hierarchy;
    }


    public static Drawable getDrawable(int id) {
        return ContextCompat.getDrawable(MagicalApplication.getContext(), id);
    }

    public static void loadBlurImage(SimpleDraweeView view, String url, String cacheKey, float radius) {
        if (view == null || TextUtils.isEmpty(url)) {
            return;
        }
        MagicalImageRequest imageRequest
                = MagicalImageRequestBuilder.newBuilderWithSource(Uri.parse(url),
                cacheKey).setAutoRotateEnabled(true)//自动旋转图片方向
                .setCacheChoice(ImageRequest.CacheChoice.DEFAULT)//图片类型，设置后可调整图片放入小图磁盘空间还是默认图片磁盘空间
                //.setLocalThumbnailPreviewsEnabled(true)//缩略图预览，影响图片显示速度（轻微）
                .setLowestPermittedRequestLevel(
                        ImageRequest.RequestLevel.FULL_FETCH)//请求经过缓存级别  BITMAP_MEMORY_CACHE，ENCODED_MEMORY_CACHE，DISK_CACHE，FULL_FETCH
                .setPostprocessor(new FastBlurPostprocessor(radius))//动态模糊图片
                //.setProgressiveRenderingEnabled(true)//渐进加载，主要用于渐进式的JPEG图，影响图片显示速度（普通）
                .setResizeOptions(
                        (view.getLayoutParams() !=
                                null &&
                                view.getLayoutParams().width >
                                        0 &&
                                view.getLayoutParams().height >
                                        0)
                                ? new ResizeOptions(
                                view.getLayoutParams().width,
                                view.getLayoutParams().height)
                                : null)//调整大小
                .build();
        PipelineDraweeController controller
                = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setImageRequest(
                        imageRequest)
                .setOldController(
                        view.getController())
                .build();
        view.setController(controller);
    }
}