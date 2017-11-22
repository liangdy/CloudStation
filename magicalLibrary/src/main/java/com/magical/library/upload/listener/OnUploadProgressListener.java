package com.magical.library.upload.listener;

/**
 * Project: TShow
 * FileName: OnUploadProgressListener.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
public interface OnUploadProgressListener {

    /**
     *
     * @param totalSize 总大小
     * @param currSize 当前已上传的大小
     * @param progress 进度 0-100
     */
    public void onProgress(long totalSize, long currSize, int progress);

}